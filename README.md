# Base App

This is an example project that can be used as a starting point to create your own Vaadin application with Spring Boot.
It contains all the necessary configuration and some placeholder files to get you started.

The project is a standard Maven project, so you can import it to your IDE of choice. [Read more how to set up a development environment](https://vaadin.com/docs/v14/flow/installing/installing-overview.html) for Vaadin projects (Windows, Linux, macOS). 

This project was created from https://start.vaadin.com.

## Running and debugging the application

### Running the application from the command line.
To run from the command line, use `mvn` and open http://localhost:8080 in your browser.

### Running and debugging the application in Intellij IDEA
- Locate the Application.java class in the Project view. It is in the src folder, under the main package's root.
- Right-click on the Application class
- Select "Debug 'Application.main()'" from the list

After the application has started, you can view it at http://localhost:8080/ in your browser. 
You can now also attach breakpoints in code for debugging purposes, by clicking next to a line number in any source file.

### Running and debugging the application in Eclipse
- Locate the Application.java class in the Package Explorer. It is in `src/main/java`, under the main package.
- Right-click on the file and select `Debug As` --> `Java Application`.

Do not worry if the debugger breaks at a `SilentExitException`. This is a Spring Boot feature and happens on every startup.

After the application has started, you can view it at http://localhost:8080/ in your browser.
You can now also attach breakpoints in code for debugging purposes, by clicking next to a line number in any source file.
## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup. It uses [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.

## What next?

[vaadin.com](https://vaadin.com) has lots of material to help you get you started:

- Follow the tutorials in [vaadin.com/tutorials](https://vaadin.com/tutorials). Especially [vaadin.com/tutorials/getting-started-with-flow](https://vaadin.com/tutorials/getting-started-with-flow) is good for getting a grasp of the basic Vaadin concepts.
- Read the documentation in [vaadin.com/docs](https://vaadin.com/docs).
- For a bigger Vaadin application example, check out the Full Stack App starter from [vaadin.com/start](https://vaadin.com/start).

## Deploying using Docker

To build the Dockerized version of the project, run

```
docker build . -t myapp:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 myapp:latest
```
## Deploying to heroku
visit [heroku](https://vaadin.com/learn/tutorials/cloud-deployment/heroku) for details
### manual
* install heroku CLI
* `heroku plugins:install heroku-cli-deploy`
1. navigate to project dir
2. mvn package project
3. (if no heroku project yet) heroku create HEROKU_APP_NAME --no-remote
4. heroku deploy:war/jar target/LOCAL_WAR_NAME.war/jar -a HEROKU_APP_NAME
### automatic

* create heroku project config var MAVEN_SETTINGS_PATH=heroku-settings.xml
* create `heroku-settings.xml` file in root folder. npm profile not needed
* connect your github repo to heroku project
* enable auto deployments
* install heroku CLI and use heroku logs --tail -a APP_NAME to access logs
npm not needed

## h2 console
enabled by default

* search startup logs for database name
* example ```H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:ac4fce0d-18ca-40ce-8b3a-ed822626be8e'```
* visit [http://localhost:8080/h2-console](http://localhost:8080/h2-console) and update url