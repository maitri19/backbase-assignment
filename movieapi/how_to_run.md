## Pre-requisites ##

To run this solution you need to have below software installed.
-   OpenJDK 17
-   Maven
-   Docker 

## Steps ##

Run below commands in given order(assuming you have cloned the repository to your local) :

### Build ####
    mvn clean install

### Run ###

    docker-compose up --remove-orphans --force-recreate --build -d
