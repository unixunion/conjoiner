package com.deblox;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;

/**
 * Created by keghol on 11/28/13.
 */

public class PeriodicHandler implements Handler<Long> {
    String json;
    Vertx vertx;
    JsonObject config;
    MessageHandler handleMessage;

    public PeriodicHandler(String json, Vertx vertx, JsonObject config) {
        this.json = json;
        this.vertx = vertx;
        this.config = config;
    }

    public PeriodicHandler(String json, Neuron neuron) {
        //
        neuron.getVertx();
        neuron.config.getString("TOPIC_BROADCAST");
    }


    @Override
    public void handle(Long aLong) {

        this.vertx.eventBus().unregisterHandler(this.bcast);
        this.vertx.eventBus().send(config.getString("TOPIC_BROADCAST"),
                this.json,
                new Handler<Message<String>>() {
                    @Override
                    public void handle(Message<String> reply) {
                        handleMessage.handleMessage(reply, config);
                    }
                }
        );
    }
}
