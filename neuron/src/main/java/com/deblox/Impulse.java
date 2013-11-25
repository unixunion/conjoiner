package com.deblox;


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

    public Impulse() {
        this.msgType = "Unknown";
    }

    public Impulse(String msgType) {
        this.msgType = msgType;
    }

    public Impulse(String msgType, String msgBody) {
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    // srcHost
    public String getSrcHost() {
        return this.srcHost;
    }

    // dstHost
    public Impulse setDstHost(String dstHost) {
        this.dstHost = dstHost;
        return this;
    }

    public String getDstHost() {
        return this.dstHost;
    }

    // timestmap
    public long getTimestamp() {
        return this.timestamp;
    }

    // msgType
    public Impulse setMsgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    public String getMsgType() {
        return this.msgType;
    }

    // msgBody
    public Impulse setMsgBody(String msgBody) {
        this.msgBody = msgBody;
        return this;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

}


