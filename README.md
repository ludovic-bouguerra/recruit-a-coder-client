Recruit a coder plateform client part
=====================================

Recruit a coder is a web platform to automate developper recruitement process. Recruitement services can purpose technical tests to developper to know if he known languages basics.

More information at : www.recruitacoder.com

This application is the server part which compile and run the code and compare the output result with the expected result. To run this application need to be connected to a message broker like ActiveMQ or the Glassfish embedded message broker.

Actually the software can compile and compare Java Code but you can easily add other languages like PHP or C. (Documentation coming soon).

You can find the client part in another repository. https://github.com/ludovic-bouguerra/ecodigo-server/

Prerequisite
==============

Glassfish 3 or + (Full JavaEE platform)
ActiveMQ or Glassfish embedded message broker enabled.
Maven 2

Installation
============

Download the source code. 

```
mvn install
```

The war is available in target dir.

You can deploy the war in glassfish.

Configure SMTP server
=====================
Go to your glassfish web administration at localhost:4848
![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/glassfish-mail-1.png)

On the left menu in "Ressources" select Session JavaMail

![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/glassfish-mail-2.png)

Click on New to create a new JavaMail Session

![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/glassfish-mail-3.png)

You have to call it mail/codigo and configure it with your smtp server parameters.

Configure Database server
========================
You have to configure a JDBC ressource called jdbc/ecodigo.

The configuration may differs depending your database server.

For mysql follow theses instructions : http://www.albeesonline.com/blog/2008/08/06/creating-and-configuring-a-mysql-datasource-in-glassfish-application-server/


![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/database-configuration.png)

Installation authentication system
===================================
With JDBC
(Comming soon)

With File
(Comming soon)

With LDAP Systems.
(Comming soon)



Result
======

Back Office
===========

Invite a user : 
![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/backoffice-1.png)

Subject creation :
![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/backoffice-2.png)
![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/backoffice-3.png)



Front Office
============
![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/capture-ecran-recruit-a-coder.png)

Demonstration
=============

A Free demonstration is available here : http://www.recruitacoder.com


Cloud Edition
=============

A cloud edition with maintenance included will be available soon at http://www.recruitacoder.com

