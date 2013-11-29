package com.deblox;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/27/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class MessageHandler {
    /** takes care of dealing with messages **/

    private static Logger logger = Logger.getLogger("MessageHandler");

    public static void handleMessage(Message<String> message, JsonObject config) {
        // Unpack the message into Impulse object
        Impulse imsg = new Impulse().toImpulse(message.body());

        // Log the Impulse Message contents
        logger.info("Handling Message: " + imsg.toJson());
        logger.info("Message Origin: " + imsg.getSrcHost());
        logger.info("My Hostname: " + config.getString("HOSTNAME"));
        logger.info("Handler config: " + config);

        if (imsg.getMsgType().equals(MsgType.TEST)) {
            // Respond to test messages with a pong!
            Impulse rimsg = new Impulse(MsgType.TEST).setMsgBody("pong!").setHostname(config.getString("HOSTNAME"));
            message.reply(rimsg.toJson());
        } else {
            if (message.ad)
            // Determine that the message is not from myself, cause that makes me look crazy.
            else if (imsg.getSrcHost().equals(config.getString("HOSTNAME"))) {
                logger.info("Ignoring message from myself");
            } else {
                logger.info("Responding to message");
                String response = new Impulse(MsgType.ACK).setMsgBody(message.replyAddress()).setHostname(config.getString("HOSTNAME")).toJson();
                message.reply(response);
            }

        }

    }
}
