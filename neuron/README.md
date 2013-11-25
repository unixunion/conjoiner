# Maglock / Prometheus

This is a concept application for distributed management of hosts with pluggable architecture and leveraging on eventual
consistancy paradigms.

# Vertx.io

Vert.x is a lightweight, high performance application platform for the JVM. Being a polygot, you can write your
application in Javascript, Coffeescript, Java, Ruby, Python or Groovy or any combination of them. Features non-blocking
IO and single threaded designmeans its very easy to use since everything is callback orientated. At its heart, Vertx
uses Hazelcast for topic based messaging in in-memory object replication. Though Vertx does not fully support all of
hazelcasts features at this point (2.0.0.0).

# Building

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home mvn install

# Packaging

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home mvn package
Generates: target/maglock-1.0-SNAPSHOT-mod.zip

# run
There are many ways to run a module, if its in your .m2 repo
vertx runmod com.deblox~neuron~0.1

If its exploded.
vertx run Boot.java