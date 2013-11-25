package com.deblox;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import java.util.logging.Logger;
import static com.deblox.Serializer.objectToJson;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/27/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class MessageHandler {
    /** takes care of dealing with messages **/

    private static Logger LOGGER = Logger.getLogger("MessageHandler");
    JsonObject config;

    public void MessageHandler(JsonObject config) {
        config = config;
    }

    public static void handleMessage(Message<String> message) {
        LOGGER.info("Message: " + message.body());
        message.reply(objectToJson(new Impulse()));
    }
}
