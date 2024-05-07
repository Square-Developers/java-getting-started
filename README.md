# Get Started

[Install Java Guide](https://www.java.com/en/download/help/download_options.html) - If you don't have java already installed on your machine.

[Maven Install Guide](https://maven.apache.org/install.html)
**note**: Gradle is also supported but the quickstart here demonstrates using Maven. 

[Square SDK Guide](https://developer.squareup.com/docs/sdks/java/using-java-sdk) - details on how to use / configure the Square client.

[Java Quickstart guide](https://developer.squareup.com/docs/sdks/java/quick-start) - The quickstart directory is based off of this document.

[Maven Repository for Square](https://mvnrepository.com/artifact/com.squareup/square) - Where the package files are hosted

[Java SDK Source Code](https://github.com/square/square-java-sdk) - Github repo with sdk source code

## Quickstart instructions

Change into the `quickstart` directory

Copy the example config file in `src/main/resources`, and place your `Square Access Token` inside of the new file.

```
$ cp src/main/resources/config.properties.example src/main/resources/config.properties
```

Replace `PLACE_VERSION_HERE` with the latest SDK version from [here](https://developer.squareup.com/docs/sdks/java)

Compile the program
```
$ mvn package -DskipTests
```

Execute the code
```
$ mvn exec:java -Dexec.mainClass="com.square.examples.Quickstart"
```

In your console, you should see output similar to this:

```
Location(s) for this account:
LGJ1WWJ8PSV8Z: Default Test Account, 1600 Pennsylvania Ave NW, Washington
```