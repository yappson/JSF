# Use a lightweight Java image with Tomcat
FROM tomcat:9.0.84-jdk17-temurin



WORKDIR /usr/local/tomcat/webapps/

# Copy the built WAR file
COPY target/EmployeManagementSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
# Expose the port that your application will run on
EXPOSE 8080

# Command to run Tomcat
CMD ["catalina.sh", "run"]
