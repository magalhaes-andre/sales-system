## Sales System

This is a simple sales-system made in quarkus with simple CRUD operations and some service methods doing model formatting.

If you want to run this for checking locally as a developer you can do: ./gradlew quarkusDev

It is also expected to have a mongo db instance running:

_(If you don't have it) => docker run -d --name mongodb -p 27017:27017 mongo_

### _There is also a Dockerfile for building this application into a container_ (Not working yet)