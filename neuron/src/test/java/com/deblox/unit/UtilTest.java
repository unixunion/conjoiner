package com.deblox.unit;

import com.deblox.Impulse;
import junit.framework.TestCase;
import com.deblox.Util;
import java.util.logging.Logger;
import org.junit.Before;

/**
 * Created by keghol on 11/26/13.
 */
public class UtilTest extends TestCase {
    Logger logger;

    @Before
    public void setUp() {
        logger = Logger.getLogger("this");
    }

    public void testGetHostname() throws Exception {
        String hostname = new Util().getHostname();
        logger.info(hostname);
        assertNotNull(hostname);
    }

    public void testGenerateIdentifier() throws Exception {
        Integer identifier = new Util().generateIdentifier();
        logger.info(identifier.toString());
        assertNotNull(identifier);
    }
}
