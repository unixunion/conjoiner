# Conjoiner Neuron

This is a concept application for distributed management of hosts with pluggable architecture and leveraging on eventual
consistancy paradigms.


## Vertx.io

Vert.x is a lightweight, high performance application platform for the JVM. Being a polygot, you can write your
application in Javascript, Coffeescript, Java, Ruby, Python or Groovy or any combination of them. Features non-blocking
IO and single threaded designmeans its very easy to use since everything is callback orientated. At its heart, Vertx
uses Hazelcast for topic based messaging in in-memory object replication. Though Vertx does not fully support all of
hazelcasts features at this point (2.0.0.0).

## What it should do

Conjoiner Neuron should bootstrap and subscribe to a broadcast messaging bus. Nodes then request updates, announce status of
running modules and deploy other modules as instructed from the bus. Nodes also sync their module versions out to other
nodes upon request to ensure eventual module version consistancy accross the grid.

Neuron should also perhaps include or at least enable deployment of a quota enforcing module, which will enforces
the running number of instances of X or deployments of Y. and also announces / votes / responds to other nodes querying
the quota system.

## Installing

You need to install vertx.io somewhere in your path ( bin at least ). Maven should take care of the compile time depencancies.

place your config.json and cluster.json in your vertx conf dir, eg /opt/vertx-final/conf/

## Building

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home mvn install

## Packaging

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home mvn package
Generates: target/maglock-1.0-SNAPSHOT-mod.zip

## Running
There are many ways to run a module, if its in your .m2 repo
vertx runmod com.deblox~neuron~0.1 -conf /some/conf.json -cluster

If its exploded.
vertx run Boot.java