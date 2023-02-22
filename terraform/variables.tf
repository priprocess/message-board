variable "project_id" {
  type    = string
  default = "message-board123"
}

variable "region" {
  type    = string
  default = "europe-west1"
}

variable "location" {
  type    = string
  default = "europe-west1"
}

variable "name" {
  type    = string
  default = "message-board"
}

variable "image_name" {
  type    = string
  default = "eu.gcr.io/message-board123/message-board"
}


variable "bucket_name" {
  type    = string
  default = "message-board-ui"
}
