# Stage 1: Build the application using Maven
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Copy the built artifact to the Tomcat image
FROM tomcat:9.0.84-jdk17-temurin
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /usr/src/app/target/EmployeManagementSystem-0.0.1-SNAPSHOT.war jfs.war
EXPOSE 8080
CMD ["sh", "-c", "sleep 30 && catalina.sh run"]






#FROM tomcat:9.0.84-jdk17-temurin
#WORKDIR /usr/local/tomcat/webapps/
# Copy the built WAR file
#COPY target/EmployeManagementSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/jfs.war
# Expose the port that your application will run on
#EXPOSE 8080

# Command to run Tomcat
#CMD ["catalina.sh", "run"]
