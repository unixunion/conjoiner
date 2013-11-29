package com.deblox;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

import java.net.UnknownHostException;

/*
com.deblox.Neuron connects to the topics and neighbor nodes and announces itself...
passes any messages receiver to the MessageHandler
 */

public class Neuron extends Verticle {
    JsonObject config;
    Logger logger;
    String TOPIC_BROADCAST;
    String HOSTNAME;
//    String IDENTIFIER;
    MessageHandler handleMessage;
//    Handler broadcastHandler;


    // Define the periodic handler
    PeriodicHandler periodicHandler;

    private void config() {
        logger.info("Configuring Neuron");
        config = container.config();

        TOPIC_BROADCAST = config.getString("TOPIC_BROADCAST", "BROADCAST");

        try {
            HOSTNAME = config.getString("HOSTNAME", Util.getHostname());
        } catch (UnknownHostException e) {
            logger.warn("Error setting the hostname! this is bad! locate your config or fix Util.getHostname()!");
            e.printStackTrace();
            HOSTNAME = null;
        }

        logger.info("Broadcast topic: " + TOPIC_BROADCAST);
        logger.info("Hostname: " + HOSTNAME);

        // rebuild up the config object with all options as they should be.
        config.putString("HOSTNAME", HOSTNAME);
        config.putString("TOPIC_BROADCAST", TOPIC_BROADCAST);

    }

    public void start() {
        logger = container.logger();
        logger.info("Starting...");

        //config = container.config();
        this.config();

        handleMessage = new MessageHandler();
        EventBus eb = vertx.eventBus();


        Handler<Message> broadcastHandler = new Handler<Message>() {
            public void handle(Message message) {
                handleMessage.handleMessage(message, config);
            }
        };

        eb.registerHandler(config.getString("TOPIC_BROADCAST"), broadcastHandler);

        eb.registerHandler(config.getString("HOSTNAME"), broadcastHandler);


//        vertx.eventBus().registerHandler(TOPIC_BROADCAST,
//                new Handler<Message<String>>() {
//                    @Override
//                    public void handle(Message<String> message) {
//                        handleMessage(message);
//                }
//        });

//        Handler<Message> replyHandler = new Handler<Message>() {
//            public void handle(Message message) {
//                handleMessage.handleMessage(message, config);
//            }
//        };


        //


        Handler<Message> heartbeat = new Handler<Message>() {
            @Override
            public void handle(Message message) {
                logger.info("Received reply to my HEARTBEAT: " + message.body());
                handleMessage.handleMessage(message, config);
            };
        };


        // HEARTBEAT message
        vertx.setPeriodic(3000, new PeriodicHandler(new Impulse(MsgType.HEARTBEAT).setHostname(HOSTNAME).toJson(), vertx, config, bcast));


        // HEARTBEAT message
        vertx.setPeriodic(3000, new PeriodicHandler(new Impulse(MsgType.HEARTBEAT).setHostname(HOSTNAME).toJson(), this));




        container.logger().info("com.deblox.Neuron started");
    }



}
