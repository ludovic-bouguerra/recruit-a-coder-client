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
(Comming soon)

Configure Databse server
========================
(Comming soon)

Installation authentication system
===================================
(Comming soon)



Result
======

![ScreenShot](http://www.ludovicbouguerra.fr/wp-content/uploads/2013/09/capture-ecran-recruit-a-coder.png)

Demonstration
=============

A Free demonstration is available here : http://www.recruitacoder.com


Cloud Edition
=============

A cloud edition with maintenance included will be available soon at http://www.recruitacoder.com

