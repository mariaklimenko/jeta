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
import org.codehaus.jackson.map.DeserializerFactory;

/**
 * Created by yksenofontov on 01.03.2017.
 */
class RestClient {

    private final static Logger logger = Logger.getLogger(RestClient.class);

    private static WebResource getWebResource(String url, ClientConfig config, String content_type) {
        logger.info("WebResource url = " + url + " , content_type = " + content_type);
        Client client;
        if (config != null) {
            client = Client.create(config);
        } else {
            client = Client.create();
        }
        return client.resource(url);
    }

    static ClientResponse getJSONResponse(String url, String content_type) {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        WebResource webResource = getWebResource(url,config,content_type);
        return webResource.accept(content_type).get(ClientResponse.class);
    }

    static ClientResponse postJSONResponse(String url, String content_type,String body) {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        WebResource webResource = getWebResource(url,config,content_type);
        return webResource.accept(content_type).post(ClientResponse.class,body);
    }
}
