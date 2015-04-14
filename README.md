FoodService
===========

Project consists of two modules: RESTful service and Web-Site
It is flexible because model is separated from presentation layer.

**Used technologies:**
* Spring
* Hibernate, JPA
* Jersey
* JavaScript (JQuery)

**Run project steps:**
To run project you need run its modules WebService and Web-Site:

1) Make WebService up and running:
* Create new clear database for project and remember its name.
* Change database connection settings to yours in WebService/src/main/resources/orm/connection.properties
* Package project with maven
* Deploy it with Tomcat using maven plugin: mvn tomcat7:run

Now WebService is up and running with root url http://host:port/WebService/resources

2) Make foodservice.com up and running:
* Web site has a dependency of jar file WebService. So you need add this dependency into
  local maven repository.
  Go to pom.xml in WebService module and change packaging from war to jar. After that do
  install command in RESTful WebService module. Change packaging at it was at first (from jar to war).
  Now if execution of install command was successful you have a new dependency of WebService module
  in your local repo.
* Modules of the project can be run on different hosts. In the distributed mode(When
  WebService and Web-Site runs on different hosts) you need to specify
  root WebService url for Web-Site in
  foodservice.com/src/main/resources/META-INF/connection/connection.properties
* Package project with maven
* Deploy it with Tomcat using maven plugin: mvn tomcat7:run

Go to http://localhost:8083/foodservice.com and enjoy:)

Not all functionality of project is made because it is still in progress.
