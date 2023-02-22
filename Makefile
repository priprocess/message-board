# No (file) targets are assumed for most Makefile commands.
.PHONY:
	help
	build-docker
	build-docker-prod
	run
	run-jar
	run-docker-dev
	run-docker-prod
	test

help: ## Print help.
	@echo Possible commands:
	@cat Makefile | grep '##' | grep -v "Makefile" | sed -e 's/^/  - /'	

build-docker: ## Build Docker base image.
	docker build . --target base --tag message-board-base

build-docker-prod: ## Build Docker prod image.
	docker build . --target production --tag message-board-prod

run: ## Run the server.
	./mvnw spring-boot:run

run-jar: ## Package and run a jar.
	./mvnw package
	java -jar target/*.jar

run-docker-dev: ## Run docker image for development purposes.
	docker build . --target development --tag message-board-dev
	docker run --publish 8080:8080 --rm message-board-dev

run-docker-prod: ## Run docker image in production mode.
	docker build . --target production --tag message-board-prod
	docker run --publish 8080:8080 --rm message-board-prod

test: ## Run tests.
	./mvnw test

