# Client Image
FROM bellsoft/liberica-openjdk-alpine-musl:11 AS client-build
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac client/*.java

# TCP Server Image
FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server-build-tcp
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac server/*.java
CMD ["java", "server.ServerApp", "tcp", "1111"]

#UDP Server Image
FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server-build-udp
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac server/*.java
CMD ["java", "server.ServerApp", "udp", "5555"]
