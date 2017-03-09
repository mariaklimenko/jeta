package com.jeta.json;

import com.jeta.rest.entity.Comment;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yksenofontov on 02.03.2017.
 */
public class JsonConverter {
    private final static Logger logger = Logger.getLogger(JsonConverter.class);
    private final static ObjectMapper mapper = new ObjectMapper();

    public static List<Comment> convertResponseToComments(ClientResponse response) {
        logger.info("Convert response to List<Comment>");
        return response.getEntity(new GenericType<List<Comment>>() {
        });
    }

    public static Comment convertResponseToComment(ClientResponse response) {
        logger.info("Convert response to Comment");
        return response.getEntity(new GenericType<Comment>() {
        });
    }

    public static Comment getCommentBasedOnFile(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        File json_file = new File(filename);
        Comment comment = null;
        try {
            comment = mapper.readValue(json_file, Comment.class);
        } catch (IOException e) {
            logger.error("Can not convert file to json. File: " + filename);
            e.getMessage();
        }
        return comment;
    }

    public static String getJSONFromObject(Object object) {
        String json_string = "";
        try {
            json_string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            logger.error("Can not convert object to json, class " + object.getClass());
            e.getMessage();
        }
        return json_string;
    }

}