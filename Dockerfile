FROM maven:3-jdk-8

COPY . .

RUN mvn clean package -Dmaven.test.skip=true

EXPOSE 8081

#CMD [ "java", "-Doracle.jdbc.timezoneAsRegion=false", "-Xmx1000m", "-jar", "/app/target/appsmanagerws-0.0.1-SNAPSHOT.war", "--server.servlet.context-path=/amws"]
CMD [ "java", "-jar", "./target/sehati-2.0.war"]