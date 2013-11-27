package com.deblox.unit;

import com.deblox.Impulse;
import junit.framework.TestCase;
import org.junit.Before;
import com.deblox.Util;
import com.deblox.MsgType;

import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Created by keghol on 11/25/13.
 */

public class ImpulseTest extends TestCase {
    Impulse impulse;
    String srcHost;
    String dstHost;
    Logger logger;

    @Before
    public void setUp() throws UnknownHostException{
        impulse = new Impulse(MsgType.HEARTBEAT);
        srcHost = new Util().getHostname();
        logger = Logger.getLogger("this");
    }

    // Check if a created Impulse object gets the hostname of "this" host.
    public void testGetSrcHost() throws Exception {
        assertEquals(impulse.getSrcHost(), srcHost);
    }

    // Just test if we can set the Dst host to some string
    public void testSetDstHost() throws Exception {
        dstHost = "FOO";
        Impulse tmp = new Impulse(MsgType.UNKNOWN).setDstHost(dstHost);
    }

    // test if a Impulse whos dstHost is set is readable
    public void testGetDstHost() throws Exception {
        Impulse tmp = new Impulse(MsgType.UPDATE_REQUEST).setDstHost(dstHost);
        assertEquals(tmp.getDstHost(), dstHost);
    }

    public void testNewImpulseTypeAndBody() throws Exception {
        Impulse tmp = new Impulse(MsgType.TEST, "Dombody");
    }

    // Test Impulse message gets a timestamp.
    public void testGetTimestamp() throws Exception {
        assertNotNull(impulse.getTimestamp());
    }

    // Test msgType setting after the fact.
    public void testSetMsgType() throws Exception {
        impulse.setMsgType(MsgType.HEARTBEAT);
    }

    // Test reading back the msgType enum works
    public void testGetMsgType() throws Exception {
        impulse.setMsgType(MsgType.HEARTBEAT);
        assertEquals(impulse.getMsgType(), MsgType.HEARTBEAT);
    }

    // Set the msg body to some string "json normally"
    public void testSetMsgBody() throws Exception {
        impulse.setMsgBody("Fuul!");
    }

    // Test setting and reading back Body
    public void testGetMsgBody() throws Exception {
        impulse.setMsgBody("Fuul!");
        assertEquals(impulse.getMsgBody(), "Fuul!");
    }

    // Test creating a json version of an impulse class object
    public void testToJson() {
        String impulse_json = new com.deblox.Impulse(MsgType.UNKNOWN).setMsgBody("ff").toJson();
        assertNotNull(impulse_json);
    }

    // Test creating a json representation of the impulse object and converting it back to Impulse class
    public void testToImpulse() {
        String impulse_json = new Impulse(MsgType.UPDATE_RESPONSE).setMsgBody("ff").toJson();
        Impulse new_impulse = new Impulse().toImpulse(impulse_json);
        assertEquals(new_impulse.getMsgBody(), "ff");
    }
}
