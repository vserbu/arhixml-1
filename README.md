# ArhiXML

ArhiXML is used to create and maintain the digital archive used by ARHiNET information system. Digital archive itself is a file which uses a XML format powered by the XML schema. It is a web application written in Java using [Vaadin](https://vaadin.com/) web framework.

The goal of this project was to try-out and learn Vaadin. The source code is not something that I am proud of nor it does contain any good design. But apperantly, it is used in production. Maybe one day, I will rewrite it into something more better.

I strongly advise you to follow the [How To Run ArhiXML From Docker Image](#how-to-run-arhixml-from-docker-image) if you want to use ArhiXML on your own computer.

The [How To Build And Run ArhiXML From Source](#how-to-build-and-run-arhixml-from-source) is used only if you are a ArhiXML developer and want to extend its code.

## How To Run ArhiXML From Docker Image

This is the most easiest approach to running the ArhiXML. To do so, you will need [Docker](https://www.docker.com/).

Docker installation depends on the operating system. Microsoft Windows, GNU/Linux and macOS are all supported. There is a nice step-by-step [documentation](https://docs.docker.com/install/) that you can follow to install Docker for your operating system.

After you have install Docker you need to download the prebuild image by executing following command in your terminal:

```shell
docker pull hvarga/arhixml
```

Finally, to run the ArhiXML, execute following command also from your terminal:

```shell
docker run --network host --rm hvarga/arhixml
```

That's it! Your ArhiXML instance is ready to use. Just open up your web browser and type `http://localhost:8080/` into your address bar.

## How To Build And Run ArhiXML From Source

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