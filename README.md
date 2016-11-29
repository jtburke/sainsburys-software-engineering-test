# Sainsbury's Software Engineering Test

It's overengineered for the purpose but is built in a similar way to
how I'd structure a production service.

Apolgies for using PowerMock, it was to drive JSoup tests. Not something
I'd use out of preference.

I chose to create manual dependency injection modules given the small size
of the project.

## Building the project
`./gradlew`

## Running the project
`./runJar.sh`  
  
or  
  
`java -jar adapter-app/build/libs/adapter-app-all.jar`