Webservice clients
==================

Example of how to generate webservice clients with cxf and axis2.

```
> git clone https://github.com/Transmode/webservice_clients.git
> cd webservices_client
```

To build everything:

```
> ./gradlew build -Pserver=http://myserver.example.com:8080
```

To build only one client:

```
> ./gradlew :cxf_mer:build -Pserver=http://myserver.example.com:8080
```

Known problems
==============

axis2 codegen fails to generate valid java code with the current 
release of the webservices.


