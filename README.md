Project Manager - Final certification case study

"# project-manager-final" Project Manager Case Study Build Notes:

project-manager-server - Maven Spring boot project for exposing rest endpoints + MySql project-manager-web - Angular CLI for building UI and connects with rest endpoints exposed

Git Repository:https://github.com/srimeenakshigjgithub/project-manager-final.git/

Maven Build Commands for the final artifacts:

clean install -e	[run the command for project-manager-server project which will build UI and service project and create the final jar with required resources]

Commands for local development:

Make sure mysql is running in local machine Run maven command spring-boot:run for the project task-manager-server Check whether the endpoint is working fine in postman Endpoint : http://localhost:8686 Open the folder src/main/web of project project-manager-web in visual studio and then run below commands npm install npm start Hit the url http://loalhost:4200 and see whether the page is getting loaded

Jenkins command: Make sure Jenkins is installed and running Configure Maven and JDK in jenkins with name maven3 and jdk1.8 Create Jenkins project with Pipeline option and configure the below information i) Github repository with credentials ii) Branch to build: */master iii) Path to Jenkinsfile

Documents for Reference:

project-manager-server - service code 
project-manager-web - Angular UI code 
project-manager-scripts - table scripts to run in DB 
Jenkins file - Jenkins details. 
Docker file - docker details.

1. Screenshots Folder 
       Code coverage screen shots
       Load testing results screen shots
       Application screen shots
       Jenkins screen shots

2. MySQLScripts Folder - project-manager-scripts.sql - DB scripts

3. Reports folder
      CodeCoverage - Emma code coverage reports will be available
      Jenkins - Jenkins details 
      LoadTesting - jMeter load testing report is available

4.  project-manager-parent - UI and Service code base
