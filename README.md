# OOAD-Project-
Apartment/Society Management System

**Steps to Run**:
1) Create a My Sql Database Called 'apartmentsociety' and connect to it using Mysql Client .
2) Use the DDL statements given in the sqlddl.txt file to create the tables required in the database .
3) Create an admin login for the application by inserting values into the adminlogin table by using:
        "INSERT into adminlogin VALUES( "User" , "Password" );
4) Open the project in Eclipse IDE . Extract the mysql-connector-java-8.0.29.jar and refrence this in the project by going to
   Build Path ==> Add External JARs and attach the mysql-connector-java-8.0.29.jar . Once that is done you can see this file in      the refrenced Libraries Section in the Project Explorer.
5) Open the Datacontext.java file and put in your Mysql user name and password in the line:
     **DriverManager.getConnection("jdbc:mysql://localhost:3306/apartmentsociety", "enterusername", "enterpassword");**
7)Run the application using the Run Button on top or by right Clicking on the main project foldr in the project explorer section.

NOTE: sql serer runs on port 3306 by default , if its running on a different port please change the port number in the Datacontext.java file as well
