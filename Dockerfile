FROM eclipse-temurin:19 AS base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base AS development
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]

FROM base AS build
RUN ./mvnw package

FROM eclipse-temurin:19 as production
EXPOSE 8080
COPY --from=build /app/target/message-board-*.jar /message-board.jar
CMD ["java", "-jar", "/message-board.jar"]
