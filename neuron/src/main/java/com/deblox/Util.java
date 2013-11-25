package com.deblox;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/26/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    // gethostname
    public String getHostname() {
        try {
            java.net.InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            return hostname;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

}


