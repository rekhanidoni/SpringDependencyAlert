This project sends email alerts to users subscribed to receive information when dependent services go down
New Dependencies can be added by developers after cloning this repo.

PRE- REQUISITES and HOWTO

1. git clone this repo
2. Install mongo locally or change mongo details in mongo.properties to match the mongo server details
3. Update application.properties with gmail credentials
4. Install maven
5. cd SpringDependencyAlert
6. Run 'mvn clean install'
7. java -jar target/Spring-Dependency-Alert-1.0-SNAPSHOT.jar


To add any new Dependency follow these steps:
1. Annotate class with 'Dependency'
2. Class should also implement DependencyInterface
3. DependencyCheck collects all beans annotated with @Dependency and monitors them

