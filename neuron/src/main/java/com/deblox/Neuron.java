package com.deblox;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;
import static com.deblox.Serializer.objectToJson;
import static com.deblox.MessageHandler.handleMessage;


/*
com.deblox.Neuron connects to the topics and neighbor nodes and announces itself...
passes any messages receiver to the MessageHandler
 */

public class Neuron extends Verticle {
    JsonObject config;
    Logger logger;
    String MSGTYPE_HEARTBEAT;
    String MSGTYPE_UPDATEREQUEST;
    String TOPIC_BROADCAST;

    private void config() {
        logger.info("Configuring...");
        config = container.config();
        MSGTYPE_HEARTBEAT = config.getString("MSGTYPE_HEARTBEAT", "HEARTBEAT");
        MSGTYPE_UPDATEREQUEST = config.getString("MSGTYPE_UPDATEREQUEST", "UPDATE_REQUEST");
        TOPIC_BROADCAST = config.getString("TOPIC_BROADCAST", "BROADCAST");
        logger.info("Configured");
    }

    public void start() {
        logger = container.logger();
        logger.info("Starting...");

        //config = container.config();
        this.config();

        vertx.eventBus().registerHandler(TOPIC_BROADCAST,
                new Handler<Message<String>>() {
                    @Override
                    public void handle(Message<String> message) {
                        System.out.println("Received message: " + message.body());
                        logger.info("Reply Address: " + message.replyAddress());
                        message.reply("pong!");
                }
        });

//        vertx.eventBus().registerHandler(TOPIC_BROADCAST,
//                new Handler<Message<String>>() {
//                    @Override
//                    public void handle(Message<String> message) {
//                        handleMessage(message);
//                }
//        });


        // HEARTBEAT message
        vertx.setPeriodic(1000, new Handler<Long>() {
            @Override
            public void handle(Long timerID) {
                vertx.eventBus().send(TOPIC_BROADCAST,
                        objectToJson(new Impulse(MSGTYPE_HEARTBEAT)),
                        new Handler<Message<String>>() {
                            @Override
                            public void handle(Message<String> reply) {
                                System.out.println("Received reply: " + reply.body());
                        }
                });
            }
        });

        container.logger().info("com.deblox.Neuron started");
    }


}
