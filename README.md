conjoiner
=========

conjoiner, a distributed management network

## Components
Conjoiner is a collection of vertx modules and verticals to achieve true distributed host management where all nodes are truely equal. 

## Features
* Distributed message driven architecture
* Standardized message payloads
* Deployment of new modules via message events
* Control of modules via message events

## Modules
### Deployer
Deployer is a core feature of Conjoiner, It should be a simple vertx application which should subscribe to both the shared eventbus and the local modbus. New deployment messages are received on the eventbus, and after determining if the message recipieint is this node, the deployment task is performed. This could include:

* Download of mod package.
* Creation of the mods/MODULENAME/mod.json manifest
* Creation of the module initial config object
* Deployment of the module with initial config
* Posting result back to EventBus
* Posting result to local modules on ModBus

![Deployer](https://raw.github.com/unixunion/conjoiner/master/deployer.png?token=1773544__eyJzY29wZSI6IlJhd0Jsb2I6dW5peHVuaW9uL2NvbmpvaW5lci9tYXN0ZXIvZGVwbG95ZXIucG5nIiwiZXhwaXJlcyI6MTM4NjM0MjA0MX0%3D--2ef3f00f924da76e9ec7a1f9a16b4e37f96e1201)


### Transponder
Transponder would be the first module to be shipped and deployed by the "Deployer" module mentioned above. Transponder is in charge of:

* Listening for events on EventBus
* Determining if messages are intended for this node
* Passing messages to the ModBus
* Serialization / Deserialization of Messages
* Adding metadata to messages eg: hostname, timestamp, messagetype…
* Heartbeat messages



### Monitor
A Monitoring module should post occasional requests as to the status of various modules on the ModBus and then notify Transponder to notify the EventBus of the status's 






