Webservice clients
==================

Example of how to generate webservice clients with CXF (and wsimport).

    git clone https://github.com/Transmode/webservice_clients.git
    cd webservices_client

To build the managed element collector
--------------------------------------

Running on the command line:

    cd mer/collector_mer   
    ../gradlew run -Pserver='http://myserver.example.com:8080'

Outputs something like this:

    ManagedElement [md=Tnm Server, me=1.1.1.1, userLabel=theName_new2, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]

    ManagedElement [md=Tnm Server, me=1.2.3.4, userLabel=Passive, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]
    
To build the equipment collector
--------------------------------

Running on the command line:

    cd mer/collector_eir   
    ../gradlew run -Pserver='http://myserver.example.com:8080'

Outputs something like this:

...
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



