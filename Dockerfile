FROM openjdk:19-alpine
#COPY --from=build /target/VkBotSpring-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/home/VkBotSpring-0.0.1-SNAPSHOT.jar"]
EXPOSE 80
