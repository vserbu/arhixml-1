# Start from minimal Alpine Linux with OpenJDK 8.
# This image is used to build the ArhiXML.
FROM openjdk:8-jdk-alpine AS builder

# Author and Docker Image information.
LABEL maintainer="hrvoje.varga@gmail.com"
LABEL build="docker build -t hvarga/arhixml ."
LABEL run="docker run --network host --rm --name arhixml hvarga/arhixml"

# Install Apache Ant.
RUN apk add apache-ant

# Copy the source code.
RUN mkdir /root/source
COPY . /root/source

# Build ArhiXML.
WORKDIR /root/source
RUN ant

# Start from minimal Alpine Linux with OpenJRE 8.
# This image is used as a runtime environment for ArhiXML.
FROM openjdk:8-jre-alpine AS runner

WORKDIR /root/
COPY --from=builder /root/source/dist/ArhiXML.war .
CMD ["java", "-jar", "ArhiXML.war"]