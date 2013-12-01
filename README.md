Conjoiner
=========
"To join or become joined together; unite."

Conjoiner is intended to be a truely distributed management network for large sets of hosts. There are no central management servers or messaging servers. All nodes are equal.

## Schematic
![Conjoiner]()

## Core Features
* Distributed message driven architecture
* Standardized message payloads
* Deployment of new modules via message events
* Passing messages between local mods and verticals within the instance and the cluster.

## Glossary
#### local-ModBus
The local servers "internal" messaging bus, this is actually on the cluster but the addressing schema is only known by the local machine and is something down the line of: myserver.mydomain.com.local 
#### ClusterBus
The shared messaging network with the entire cluster. The topic name is known to ALL instances on all servers. eg: "conjoiner.clusterbus"


## Components
Conjoiner is a grouping of some core modules, these modules are interacted with via messaging. The core of Conjoiner provides messaging capabilities to the Cluster and the ability to deploy new code and attach that code to the Cluster though indirectly via the "transponder". See transponder below.


## Modules
### Deployer
The Deployer is a core feature of Conjoiner, It should be a simple module which should subscribe to both the ClusterBus and the local-ModBus. Deploy / Undeploy messages are received on the ClusterBus, and after determining if the message is intended for this node, the deployment task is performed. This could include:

* Download of mod package from nexus
* Creation of the mods/MODULENAME/mod.json manifest within conjoiner
* Creation of the module's initial config object
* Deployment of the module with its initial config
* Publishing Deploy/Undeploy result back to ClusterBus
* Notifying local modules on local-ModBus of the deployment change

![Deployer](https://raw.github.com/unixunion/conjoiner/master/deployer.png?token=1773544__eyJzY29wZSI6IlJhd0Jsb2I6dW5peHVuaW9uL2NvbmpvaW5lci9tYXN0ZXIvZGVwbG95ZXIucG5nIiwiZXhwaXJlcyI6MTM4NjM0MjA0MX0%3D--2ef3f00f924da76e9ec7a1f9a16b4e37f96e1201)


### Transponder
[Transponder at GitHub](https://github.com/unixunion/transponder.git)

Transponder would be the first module to be shipped and deployed by the "Deployer" module mentioned above. Transponder is in charge of:

* Listening for events on EventBus
* Determining if messages are intended for this node
* Passing messages to the ModBus
* Serialization / Deserialization of Messages
* Adding metadata to messages eg: hostname, timestamp, messagetype…
* Heartbeat messages

![Transponder](https://raw.github.com/unixunion/conjoiner/master/transponder.png?token=1773544__eyJzY29wZSI6IlJhd0Jsb2I6dW5peHVuaW9uL2NvbmpvaW5lci9tYXN0ZXIvdHJhbnNwb25kZXIucG5nIiwiZXhwaXJlcyI6MTM4NjM0MzY5M30%3D--557c82f2d83f37d3b0974bfde1b414814ecb169a)




### Monitor
A Monitoring module should post occasional requests as to the status of various modules on the ModBus and then notify Transponder to notify the EventBus of the status's






