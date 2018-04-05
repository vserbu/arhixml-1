# How to build and run the ArhiXML from the sources

The first thing is to get the project source files. This involves cloning of the [Git repository](https://github.com/hvarga/arhixml.git). Git needs to be installed in order to clone this repository. If not already installed go [here](http://git-scm.com/downloads), download and install.

After project source files are in possession, the project needs to be built. To actually build the project from the source files one must have Apache Ant tool installed onto his machine. If you don't have the tool installed download it from [here](http://ant.apache.org/bindownload.cgi) and install. The second prerequisite is to have Java Development Kit installed. If you don't have one, download it from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and install. Minimum Java SE 7 is required.

The final step is to actually start the build process using the Apache Ant. Just run the Apache Ant in the root directory of the project source files and the tool will take care of the building process.

```plain
ant
```

For the above to work, Apache Ant executable must be in a `$PATH`.

After Apache Ant successfully completes the building process ArhiXML application is ready for use. You can now start the application with:

```plain
java -jar dist/ArhiXML.war
```

This will start the embedded servlet container which will in turn host the ArhiXML application. You can also install the application on a dedicated servlet container like Apache Tomcat, which is preferred.