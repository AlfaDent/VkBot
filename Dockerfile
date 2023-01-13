FROM openjdk:19-alpine
COPY /target/VkBotSpring-0.0.1-SNAPSHOT.jar home/app.jar
EXPOSE 8000
CMD ["java", "-jar", "/home/app.jar"]
