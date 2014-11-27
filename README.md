Webservice clients
==================

Example of how to generate webservice clients with CXF (and wsimport).

    git clone https://github.com/Transmode/webservice_clients.git
    cd webservices_client

Running the managed element collector
-------------------------------------

Running on the command line:

    cd mer/collector_mer   
    ../gradlew run -Pserver='http://myserver.example.com:8080'

Outputs something like this:

    ManagedElement [md=Tnm Server, me=1.1.1.1, userLabel=theName_new2, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]

    ManagedElement [md=Tnm Server, me=1.2.3.4, userLabel=Passive, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]
    
Running the equipment collector
-------------------------------

Running on the command line:

    cd mer/collector_eir   
    ../gradlew run -Pserver='http://myserver.example.com:8080'

Outputs something like this:

    1: Equipment: name=passive:1, subrack=1, slot=-1
    2: Equipment: name=passive:1, subrack=1, slot=-1
    3: Equipment: name=passive:1, subrack=1, slot=-1
    4: Equipment: name=co10:1:10, subrack=1, slot=10
    5: Equipment: name=passive:1, subrack=1, slot=-1
    ...

Running the TP collector
------------------------

Run on the command line: 

    cd tpr/collector_tpr
    ../gradlew run -Pserver='http://myserver.example.com:8080'
    
Should print something like this:

    name=/MD=Tnm Server/ME=1.1.10.1/PTP=passive:1:1:6/direction=sink, userLabel=passive:1:1:6
    name=/MD=Tnm Server/ME=172.18.1.11/PTP=wdm:1:5:9-10, userLabel=wdm:1:5:9-10, rxSignalStatus=3
    name=/MD=Tnm Server/ME=172.18.1.11/PTP=client:1:5:1-2, userLabel=client:1:5:1-2, rxSignalStatus=1
    ...
    
Note: The TP collector only retrieves the first batch of termination points and
only prints the physical termination points.




