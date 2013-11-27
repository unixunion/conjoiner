package com.deblox;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/26/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    // gethostname
    public static String getHostname() throws UnknownHostException{
        java.net.InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        return hostname;
    }

    // Generates a hash based of the timestamp this was executed. Not super accurate but good enough for testing
    // this shit locally.
    public static Integer generateIdentifier() throws UnknownHostException {
        try {
            Integer uniqueIdentifier = new String(getHostname() + String.valueOf(new Date().getTime())).hashCode();
            return uniqueIdentifier;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


