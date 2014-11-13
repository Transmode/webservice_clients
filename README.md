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

1: Equipment: name=passive:1, subrack=1, slot=-1
2: Equipment: name=passive:1, subrack=1, slot=-1
3: Equipment: name=passive:1, subrack=1, slot=-1
4: Equipment: name=co10:1:10, subrack=1, slot=10
5: Equipment: name=passive:1, subrack=1, slot=-1
6: Equipment: name=passive:1, subrack=1, slot=-1
7: Equipment: name=tm301:1, subrack=1, slot=-1
8: Equipment: name=cusfp:1:1, subrack=1, slot=1
9: Equipment: name=voa8chsfp:1:2, subrack=1, slot=2
10: Equipment: name=port:1:2:1-2:VOA1, subrack=1, slot=2
11: Equipment: name=port:1:2:5-6:VOA3, subrack=1, slot=2
12: Equipment: name=port:1:2:11-12:VOA6, subrack=1, slot=2
13: Equipment: name=port:1:2:15-16:VOA8, subrack=1, slot=2
14: Equipment: name=undefined:1:4, subrack=1, slot=4
...


