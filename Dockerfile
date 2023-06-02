FROM maven:3.9.2-eclipse-temurin-20
COPY . .
RUN mvn package
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -jar target/demo-be.jar"]
