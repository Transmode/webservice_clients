collector
=========

Managed element collector
-------------------------

Running on the command line:

    cd mer/collector_mer   
    ../gradlew run -Pserver='http://anotherserver:8080'

Outputs something like this:

    ManagedElement [md=Tnm Server, me=1.1.1.1, userLabel=theName_new2, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]

    ManagedElement [md=Tnm Server, me=1.2.3.4, userLabel=Passive, resourceState=INSTALLING_INSTALLED, location=, productName=Passive, isInSyncState=false, communicationState=CS_UNAVAILABLE]
    
