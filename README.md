# My InternetShop project




# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

# <a name="purpose"></a>Project purpose
This is a template for creating an e-store.
<hr>

Everyone can see all available items in the store. Users can register and then log-in for
getting possibilities to add items to their personal bucket and make orders.

There are specific tools for admins only to allow them adding items to the store and viewing 
a list of all registered users.
<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.8.0
* maven-checkstyle-plugin
* javax.servlet-api 3.1.0
* jstl 1.2
* mysql-connector-java 8.0.18
* log4j 1.2.17
<hr>

# <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Choose sdk 11.0.3 in Project struсture.

3. Configure Tomcat:
add artifact my_internet_shop_war_exploded;
add as URL http://localhost:8080/my_internet_shop_war_exploded/index 

4. Start MySQLWorkbench.

5. At my_internet_shop.src.main.java.mate.academy.internet.shop.factories.Factory class use your username 
and password for your MySQLWorkbench to create a Connection.

6. Run my_interntet_shop.src.main.resources.init_db.sql to create all the tables required by this app.

7. Change a path in my_interntet_shop.src.main.resources.log4j.properties. It has to reach your logFile.

8. Run the project.

There’s one user already registered with ADMIN role (email = "admin@yahoo.com"", password = 1) and
one user with USER role (email = "user@yahoo.com"", password = 2).
Also there are two items in the store: a book and a flower:).

# <a name="author"></a>Author

Iegor Soboliev https://github.com/IegorSoboliev



