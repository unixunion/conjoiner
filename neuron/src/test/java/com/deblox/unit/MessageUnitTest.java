package com.deblox.unit;

import com.deblox.Impulse;
import org.junit.Test;
import java.util.logging.Logger;

import static com.deblox.Serializer.jsonToClass;
import static com.deblox.Serializer.objectToJson;
import static com.deblox.Serializer.jsonToObject;


public class MessageUnitTest {
    Logger logger = Logger.getLogger("this");

    @Test
    public void testSeriaizeImpulse() {
        com.deblox.Impulse impulse = new com.deblox.Impulse("HEARTBEAT");
        impulse.setMsgBody("ff");
        String impulse_serialized = objectToJson(impulse);
        logger.info(impulse_serialized);
    }

    @Test
    public void testSeriaizeImpulse2() {
        String impulse_serialized = objectToJson( new com.deblox.Impulse("HEARTBEAT"));
        logger.info(impulse_serialized);
    }

    @Test
    public void testDeserializeImpulse() {
        String impulse_serialized = "{\"com.deblox.Impulse\":{\"srcHost\":\"STHMACLT009.local\",\"timestamp\":1380197332986}}";
        com.deblox.Impulse impulse = (Impulse)jsonToObject(impulse_serialized);
        logger.info(impulse.getSrcHost());
    }
}


