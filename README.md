This project sends email alerts to users subscribed to receive information when dependent service go down

PREREQUISITES

1. git clone this repo
2. Install mongo locally or change mongo details in mongo.properties to match the mongo server details
3. Update application.properties with gmail credentials
4. Install maven
5. cd SpringDependencyAlert
6. Run 'mvn clean install'
7. java -jar target/Spring-Dependency-Alert-1.0-SNAPSHOT.jar