# Project Title
Simple overview of use/purpose.
Restaurant Polling Application to receive the polling options by uers.
## Description

This application is designed for the purpose of polling the restaurant options by the users.
A user can initiate a session and other users will receive the invite to join the session to poll their desired restaurants. A user can poll only once.
Once the session deactivated by the user who untiated it, then polling completes and users who are in the session can able to view the selected restaurant. 

## Getting Started

### Dependencies

* Language Java 8.0
* spring boot version 3.0
* Maven
* spring security
* Thyme Leaf
* DB H2
* 
### Installing

* Please clone the application from the repository path and try to import the project in IDE.
* Build the Project.
* Project is using inbuild H2 Data base.
* Please register the users initially using the rest api end points by using postman. Kindly refer Json Schema and Swagger OPEN API 3.0 Doc for Request and Response Model. 

### Executing program

* How to run the program
* Step 1 : To register users using postman
* Step 2 : Login with username and password.
* Step 3 : Login with a user and initiate a session.
* Step 4 : Other users can login and join the invite to poll their desired restaurant. You will get a confirmation message stating that the options polled successfully. 
* Step 5 : Validation implemented for session and restaurant option creation.
* Step 6 : Once the session deactivated by the user who initiated it. Other users can see the result.

## Author

Venkateshwaran Rajamani  
ex.(https://linkedin.com/in/venkatrokr)

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the [venkateshwaran Rajamani]

## Acknowledgments

Spring boot Docs.
