# Jumbo app

This app has two app, an angular app for the frontend and for the backend a spring application.

Spring boot 2.5.2 / 
Angular

This app will help you to look up the stores closest to a specific point, base on the latitude and longitude

Security:
The unique user for this test is username= user and password = user1

The front-end and backend use jwt for authentication 

Excecute:
For execution the spring project use the locale profile, in the next release im going to incluide another profile for cloud deployment

THE Locator project  is the spring app, you need to install maven and java.
Configure the env variable JAVA_HOME, it must be java 11.

for windows
mvnw.cmd  spring-boot:run -Dspring-boot.run.profiles=local


Executing  spring maven project test
./mvnw test


For the angular app

ng serve -o

(you will need node)





# Jumbo
