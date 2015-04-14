FoodService
===========

Project consists of two modules: RESTful service and Web-Site
It is flexible because model is separated from presentation layer.
Module 

**Used technologies:**
* Spring
* Hibernate, JPA
* Jersey
* JavaScript (JQuery)

To run project you need do the following:
* Web site has a dependency of jar file WebService. So you need go to pom.xml
  in WebService module



**Run project steps:**
* Create new database for FoodService
* Change database settings to yours in src/main/resources/META-INF/connection/connection.properties
* Package project with maven
* Deploy it with Tomcat using maven plugin: mvn tomcat7:run

Then go to http://localhost:8081/FoodService/ and enjoy:)