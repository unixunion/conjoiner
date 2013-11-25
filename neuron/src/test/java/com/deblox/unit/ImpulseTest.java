package com.deblox.unit;

import com.deblox.Impulse;
import junit.framework.TestCase;
import org.junit.Before;
import com.deblox.Util;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.deblox.Serializer.objectToJson;

/**
 * Created by keghol on 11/25/13.
 */
public class ImpulseTest extends TestCase {
    Impulse impulse;
    String srcHost;
    String dstHost;
    Logger logger;

    @Before
    public void setUp() {
        impulse = new Impulse("HEARTBEAT");
        srcHost = new Util().getHostname();
        logger = Logger.getLogger("this");
    }

    public void testGetSrcHost() throws Exception {
        assertEquals(impulse.getSrcHost(), srcHost);
    }

    public void testSetDstHost() throws Exception {
        dstHost = "FOO";
        Impulse tmp = new Impulse("TARGETED").setDstHost(dstHost);
    }

    public void testGetDstHost() throws Exception {
        Impulse tmp = new Impulse("TARGETED").setDstHost(dstHost);
        assertEquals(tmp.getDstHost(), dstHost);
    }

    public void testGetTimestamp() throws Exception {
        assertNotNull(impulse.getTimestamp());
    }

    public void testSetMsgType() throws Exception {
        impulse.setMsgType("HA");
    }

    public void testGetMsgType() throws Exception {
        impulse.setMsgType("HA");
        assertEquals(impulse.getMsgType(), "HA");
    }

    public void testSetMsgBody() throws Exception {
        impulse.setMsgBody("Fuul!");
    }

    public void testGetMsgBody() throws Exception {
        impulse.setMsgBody("Fuul!");
        assertEquals(impulse.getMsgBody(), "Fuul!");
    }
}
