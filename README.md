# Jumbo app

This app has two app, an angular app for the frontend and for the backend a spring application.

Spring boot 2.5.2
Angular

This app will help you to look up the stores closest to a specific point, base on the latitude and longitude

Security:
The unique user for this test is username= user and password = user1

The front-end and backend use jwt for authentication 

Excecute:
For execution the spring project use the locale profile, in the next release im going to incluide another profile for cloud deployment

./mvnw spring-boot:run \
  -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local"

Executing  spring maven project test
./mvnw test


For the angular app

ng serve -o

(you will need node)





# Jumbo
