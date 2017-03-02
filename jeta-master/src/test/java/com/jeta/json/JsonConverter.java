package com.jeta.json;

import com.jeta.rest.entity.Comment;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by yksenofontov on 02.03.2017.
 */
public class JsonConverter {

    private final static Logger logger = Logger.getLogger(JsonConverter.class);
    public static List<Comment> convertResponseToComments(ClientResponse response) {
        logger.info("Convert response to List<Comment>");
        return response.getEntity(new GenericType<List<Comment>>(){});
    }
}