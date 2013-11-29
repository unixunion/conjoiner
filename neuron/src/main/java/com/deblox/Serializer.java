package com.deblox;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.util.logging.Logger;

//import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created with IntelliJ IDEA.
 * User: keghol
 * Date: 9/26/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */


public class Serializer {
    private static XStream xstream = new XStream(new JettisonMappedXmlDriver());
    private static Logger logger = Logger.getLogger("Serializer");

    // Method that returns a json-ified version of a object
    public static String objectToJson(Object object) {
        //return new XStream(new JettisonMappedXmlDriver()).toXML(object);
        logger.info("objectToJson: " + object);
        return xstream.toXML(object);
    }


    public static Object jsonToObject(String json) {
        //return new XStream(new JettisonMappedXmlDriver()).toXML(object);
        logger.info("jsonToObject: " + json);
        return xstream.fromXML(json);
    }

    public static Object jsonToClass(String json) {
        // figure out the object class and deserialize into
        // a instance of the original class

        // We dont know the class yet, ...
        Object object = jsonToObject(json);

        System.out.println("class: " + object.getClass());
        return null;

    }
}

