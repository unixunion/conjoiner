package com.deblox;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Future;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/24/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */



public class Boot extends Verticle{
    Logger logger;
    JsonObject config;

    public void start(final Future<Void> startedResult) {
        logger = container.logger();
        logger.info("Booting...");


        // load neuron_conf from config file
        config = container.config();
        JsonObject neuronConfig = config.getObject("neuron_conf");
        JsonObject webserverConfig = config.getObject("webserver_conf");

        logger.info("Configuration read: " + neuronConfig);

        logger.info("Deploying com.deblox.Neuron...");
        container.deployVerticle("com.deblox.Neuron", neuronConfig ,new AsyncResultHandler<String>() {
            public void handle(AsyncResult<String> deployResult) {
                if (deployResult.succeeded()) {
                    startedResult.setResult(null);
                } else {
                    startedResult.setFailure(deployResult.cause());
                }
            }
        });

        logger.info("Deploying Webserver...");
        container.deployModule("io.vertx~mod-web-server~2.0.0-final", webserverConfig, new AsyncResultHandler<String>() {
            public void handle(AsyncResult<String> deployResult) {
                if (deployResult.succeeded()) {
                    startedResult.setResult(null);
                } else {
                    logger.error("Well, that http server didn't fly so well");
                    startedResult.setFailure(deployResult.cause());
                }
            }
        });

    }

}
