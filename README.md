# cinema-project
# Internet shop
   * [Short Description](#description)
   * [Project Structure](#structure)
   * [Setup Guide](#setup)
   * [Authors](#authors)
# <a name="description"></a> Short Description 
   This project represents the simple back-end functionality of a movie ticket booking REST-ful web application.
   The following entities are used: ChinemaHall, Movie, MovieSession, Order, Role, ShoppingCart, Ticket, User.   
## Functions available for all users:
   * log in
   * log out
   * registration
### Functions available for all authorized users:
   * get all cinema halls
   * get all movies
   * get all movie sessions
### Functions available for users with "ADMIN" role:
   * add cinema halls
   * add movies
   * add movie sessions
   * get users
### Functions available for users with "USER" role:
   * complete orders
   * get history of own orders
   * add movie sessions to shopping cart
   * get shopping cart
   * complete orders
   * get orders
# <a name="description"></a> Project Structure
  * java 11
  * Maven 4.0.0
  * javax.servlet-api 3.1.0
  * spring-context, spring-orm, spring-webmvc 5.2.6.RELEASE
  * spring-security-core, spring-security-config,
  spring-security-web 5.3.2.RELEASE
  * hibernate-core, hibernate-java8 5.4.15.Final
  * hibernate-validator 6.1.5.Final
  * javax.annotation-api 1.3.2
  * jackson-databind 2.11.0
  * mysql-connector-java 8.0.20
  * maven-checkstyle-plugin
# <a name="description"></a> Setup Guide
##### To run this project you need to have installed:
  * Java 11+
  * Tomcat 
  * MySql
##### Setup project
* Add this project to your IDE as Maven project.
* Configure java sdk in ProjectStructure
* Add new Tomcat Server configuration and select **war-exploded artifact** to deploy.
Set application context parameter to "/".
* Create schema "cinema" in MySQL RDBMS.
* Enter your own username and password in **src/main/resources/db.properties**.
* Run the project via Tomcat configuration.
 
 For default to DB have to users:
  1. Email: admin@gmail.com; Password: password; Roles: ADMIN.
  2. Email: user@gmail.com; Password: userpass; Roles: USER.
# <a name="authors"></a> Authors
Maksym Vakuliuk