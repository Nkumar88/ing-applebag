FROM openjdk:11
EXPOSE 8080
ADD target/ing-applebag.jar ing-applebag.jar
ENTRYPOINT ["java","-jar","/ing-applebag.jar"]