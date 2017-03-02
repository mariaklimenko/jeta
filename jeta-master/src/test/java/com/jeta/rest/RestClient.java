package com.jeta.rest;

import com.jeta.json.JsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

/**
 * Created by yksenofontov on 01.03.2017.
 */
class RestClient {

    private final static Logger logger = Logger.getLogger(RestClient.class);

    static ClientResponse getJSONResponse(String url, String content_type) {
        logger.info("GET url = " + url +" , content_type = " + content_type);
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        WebResource webResource = client.resource(url);
        return webResource.accept(content_type).get(ClientResponse.class);
    }
}
