FROM openjdk:17-jdk
WORKDIR /app
COPY target/blackjack-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "blackjack-0.0.1-SNAPSHOT.jar"]