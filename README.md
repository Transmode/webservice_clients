Webservice clients
==================

Example of how to generate webservice clients with cxf and axis2.

    git clone https://github.com/Transmode/webservice_clients.git
    cd webservices_client

To build the collector
----------------------

Running on the command line:

    cd collector   
    ../gradlew run -Pserver='http://anotherserver:8080'

Outputs something like this:

    ManagedElement [md=Tnm Server, me=1.1.1.1, userLabel=theName_new2, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]

    ManagedElement [md=Tnm Server, me=1.2.3.4, userLabel=Passive, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]
    


To build everything
-------------------

    ./gradlew build -Pserver=http://myserver.example.com:8080

To build only one client:

    ./gradlew :cxf_mer:build -Pserver=http://myserver.example.com:8080

Known problems
--------------

Axis2 1.6.x fails to generate valid java code.
Possibly this is related to the following ticket:
https://issues.apache.org/jira/browse/AXIS2-5319



