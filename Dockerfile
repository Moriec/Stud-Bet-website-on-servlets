FROM maven:3.9-eclipse-temurin-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk11-temurin

COPY --from=builder /app/target/semestrovka1-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

ENV STUDBET_DB_URL=jdbc:postgresql://db:5432/studbetdb
ENV DB_USER=studbetuser
ENV DB_PASSWORD=studbetpass

EXPOSE 8080

CMD ["catalina.sh", "run"]
