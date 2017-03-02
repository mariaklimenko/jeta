package com.jeta.json;

import com.jeta.rest.entity.Comment;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import java.util.List;

/**
 * Created by yksenofontov on 02.03.2017.
 */
public class JsonConverter {

    public static List<Comment> convertResponseToComments(ClientResponse response) {
        return response.getEntity(new GenericType<List<Comment>>(){});
    }
}