package com.deblox;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class HttpServer extends Verticle {
    JsonObject config;
    Logger logger;
    String TOPIC_BROADCAST;
    String HOSTNAME;

    private void config() {
        logger.info("Configuring HttpServer");
        config = container.config();



    }

    public void start() {
        logger = container.logger();
        logger.info("Starting HTTP Server");
        this.config();

        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                logger.info("req.path: " + req.path() + " req.method: " + req.method());
                String file = req.path().equals("/") ? "index.html" : req.path();
                req.response().sendFile("webroot/" + file);
            }
        }).listen(8080);
    }
}