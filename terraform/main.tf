terraform {
  required_version = ">= 1.3"

  required_providers {
    google = ">= 4.53"
  }
}

provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_project_service" "run_api" {
  project = var.project_id
  service = "run.googleapis.com"
  disable_on_destroy = true
}

resource "google_cloud_run_service" "run_service" {
  project = var.project_id
  name = var.name
  location = var.location

  template {
    spec {
      containers {
        image = var.image_name
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }

  depends_on = [google_project_service.run_api]
}

# Allow unauthenticated users to invoke the service.
resource "google_cloud_run_service_iam_member" "run_all_users" {
  service  = google_cloud_run_service.run_service.name
  location = google_cloud_run_service.run_service.location
  role     = "roles/run.invoker"
  member   = "allUsers"

  depends_on = [google_cloud_run_service.run_service]
}

output "service_url" {
  value = google_cloud_run_service.run_service.status[0].url
}

resource "google_storage_bucket" "ui" {
  name = var.bucket_name
  location = var.location

  website {
    main_page_suffix = "index.html"
  }
  cors {
    origin          = ["*"]
    method          = ["GET", "HEAD", "PUT", "POST", "DELETE"]
    response_header = ["*"]
    max_age_seconds = 3600
  }

}

resource "google_storage_bucket_object" "ui" {
  name   = "index.html"
  source = "../index.html"
  bucket = google_storage_bucket.ui.name

  depends_on = [google_storage_bucket.ui]
}

resource "google_storage_default_object_access_control" "public_rule" {
  bucket = google_storage_bucket.ui.name
  role   = "READER"
  entity = "allUsers"

  depends_on = [google_storage_bucket.ui]
}

output "link" {
  value = google_storage_bucket_object.ui.self_link
}


