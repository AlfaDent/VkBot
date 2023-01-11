FROM openjdk:19-alpine
COPY target/VkBotSpring-0.0.1-SNAPSHOT.jar VkBotSpring-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "VkBotSpring-0.0.1-SNAPSHOT.jar"]
