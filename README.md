FORMAT: 1A
HOST:

# Application 

It is a simple application for encoding/decoding

## Build application
    export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 //linux/mac or
    set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8    //windows
	mvn package

## Start server
	mvn spring-boot:run

## Access application
    http://localhost:8080/

## Web services

### Encode [GET /encode?query={query}&locale={locale}]

### Decode [GET /decode?query={query}&locale={locale}]

### Get Available Locales [GET /locales]


