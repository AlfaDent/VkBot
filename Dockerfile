FROM openjdk:19-alpine
COPY /target/VkBotSpring-0.0.1-SNAPSHOT.jar home/app.jar
CMD ["java", "-jar", "/home/app.jar"]
EXPOSE 80
