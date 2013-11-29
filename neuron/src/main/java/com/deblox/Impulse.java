package com.deblox;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.net.UnknownHostException;
import java.util.Date;

import static com.deblox.Serializer.jsonToObject;
import static com.deblox.Serializer.objectToJson;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/25/13
 * Time: 12:56 PM
 *
 * The Impulse object is the message instance generator which is used to create a message, pack it with payload
 * and take care of serializing and de-serializing.
 *
 */

@XStreamAlias("impulse")
public class Impulse
{

    @XStreamAsAttribute
    private String srcHost;
    @XStreamAsAttribute
    private String dstHost;
    @XStreamAsAttribute
    private long timestamp = new Date().getTime();
    @XStreamAsAttribute
    private MsgType msgType; // HEARTBEAT, UPDATE_REQUEST, ...
    @XStreamAsAttribute
    private String msgBody;

    public void setHostname() {
        try {
            setHostname(Util.getHostname());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            setHostname("Unknown");
        }
    }

    public Impulse setHostname(String hostname) {
        if (hostname != null) {
            srcHost = hostname;
        } else {
            setHostname();
        }
        return this;
    }

    // Default to unknown message type
    public Impulse() {
        // DONT PUT ANYTHING IN HERE, FUCKS WITH THE DESERIALIZATION PROCESS EG:
        // Impulse foo = Impulse().toImpulse(message.body());
    }

    // New instance with type string eg: HEARTBEAT / DEPLOY / DIE
    public Impulse(MsgType msgType) {
        //this.msgType = msgType;
        this(msgType, null);
    }

    // New instance with type and body
    public Impulse(MsgType msgType, String msgBody) {
        this.msgType = msgType;
        this.msgBody = msgBody;
        this.setHostname();
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
    public Impulse setMsgType(MsgType msgType) {
        this.msgType = msgType;
        return this;
    }

    // return the message type string ( perhaps rework this to something like Impulse.HEARTBET
    public MsgType getMsgType() {
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


