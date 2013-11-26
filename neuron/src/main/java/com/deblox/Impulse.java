package com.deblox;

import static com.deblox.Serializer.objectToJson;
import static com.deblox.Serializer.jsonToObject;
import org.vertx.java.core.json.JsonObject;
import java.util.Date;
import com.deblox.Util;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/25/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */

@XStreamAlias("impulse")
public class Impulse
{
    @XStreamAsAttribute
    private String srcHost = new Util().getHostname();
    @XStreamAsAttribute
    private String dstHost;
    @XStreamAsAttribute
    private long timestamp = new Date().getTime();
    @XStreamAsAttribute
    private String msgType; // HEARTBEAT, UPDATE_REQUEST, ...
    @XStreamAsAttribute
    private String msgBody;

    // Default to unknown message type
    public Impulse() {
        this.msgType = "Unknown";
    }

    // New instance with type string eg: HEARTBEAT / DEPLOY / DIE
    public Impulse(String msgType) {
        this.msgType = msgType;
    }

    // New instance with type and body
    public Impulse(String msgType, String msgBody) {
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    // get the SrcHost of a message
    public String getSrcHost() {
        return this.srcHost;
    }

    // set the DstHost of the message, return the Impulse instance
    public Impulse setDstHost(String dstHost) {
        this.dstHost = dstHost;
        return this;
    }

    // get the DstHost of a message
    public String getDstHost() {
        return this.dstHost;
    }

    // get the message creation time
    public long getTimestamp() {
        return this.timestamp;
    }

    // set message type to something eg; HEARTBEAT / DIE / DEPLOY
    public Impulse setMsgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    // return the message type string ( perhaps rework this to something like Impulse.HEARTBET
    public String getMsgType() {
        return this.msgType;
    }

    // set msgBody to something
    public Impulse setMsgBody(String msgBody) {
        this.msgBody = msgBody;
        return this;
    }

    // get the message body
    public String getMsgBody() {
        return this.msgBody;
    }

    // return a json representation of this instance.
    public String toJson() {
        return objectToJson(this);
    }

    // Return a Impulse object of a json string.
    public Impulse toImpulse(String json) {
        return (Impulse)jsonToObject(json);
    }

}


