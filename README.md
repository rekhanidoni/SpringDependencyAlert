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



APIs

1. POST http://<ip>:8080/signup is the api to be used by users to register themselves as subscribers to email alerts. Here "ip" is the ip of machine on which this application is running. "localhost" can be used if the application is running on the same machine.
    
     Request Body to be used:
  
      {
	      "name":"xxxx",
	      "emailId":"xxxx@gmail.com"
      }
      
 2. GET http://<ip>:8080/health - can be used to check connectivity to the SpringDependencyAlert application
