FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
COPY .env.example .env.example

EXPOSE 8082

CMD ["sh", "-c", "if [ ! -f .env ]; then cp .env.example .env; fi && ./mvnw spring-boot:run"]
