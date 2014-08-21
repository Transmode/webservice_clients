Webservice clients
==================

Example of how to generate webservice clients with cxf and axis2.

> git clone git@github.com:Transmode/webservice_clients.git
> cd webservices_client

Update server to match your server.

To build everything:

> ./gradlew build

To build only one client:

> ./gradlew :cxf_mer:build

Known problems
==============

axis2 codegen fails to generate valid java code with the current 
release of the webservices.


