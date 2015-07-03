# WalmartDeliveryWS
System based on Dijkstra algorithm.  

Hello everyone!

This small system is a test specially created to prove my knowledge about some programming aspects. Now I'm going to give you some 
explanation regarding what to do to make it run.

Let's start with the database: there is a folder on the project's root called "db_schema". Inside there is a file named "WalmartDeliveryDB.sql", use this
to create our database schema on a MysqlServer 5, which needs to be running in prior.

The best way to do that, I believe, is using MySQL Workbench 6.x.

In the project (Java Web Dynamic Project), you can find the DBConnection class. Set username and password as needed, by default I'm using
usr = 'root' and pwd = '1234'.

<h1>Running the Project</h1>

This is very straight forward. Download the project and import it in your Eclipse IDE. Add to the project into Tomcat Server 7 and start it.
You can also generate a war file and deploy it to weblogic for example.

Once the project is running, open your browser and paste "http://localhost:8080/WalmartDeliveryWS/pages/index.html", this will give
access to the main page of the system. On right side of the navigation bar there is a 'Opçoes' dropdown button. Use it to first of all 
insert the maps you wish to test and then after under 'Rotas' fulfill the form and pick a previously saved map. Hit 'Calcular'.

The main action of the system, which is calculated and feedback the best path, was enginnered under Apache Axis Web Service, which is
a test main request. Thus any other applications in any technology can consume this service. The rest was implemented using simple servlets
structures.

A special aspect of this project is the Web Service Client. I created a 100% javascript client, with no need of extra heavy java code.

Well, I believe this is enough info to put you guys on the track to make it run. If any concerns I will be fully available.

Best regards, Cassiano R. Trevisan.
