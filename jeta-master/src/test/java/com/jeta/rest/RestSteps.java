package com.jeta.rest;

import com.jeta.json.JsonConverter;
import com.jeta.tools.DataGenerator;
import com.jeta.webdriver.SeleniumSimpleDemoSteps;
import com.sun.jersey.api.client.ClientResponse;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.configuration.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import com.jeta.rest.entity.Comment;

import java.io.File;
import java.util.List;

import static com.jeta.json.JsonConverter.getCommentBasedOnFile;

/**
 * Created by yksenofontov on 01.03.2017.
 */
public class RestSteps {
    private final static Logger logger = Logger.getLogger(SeleniumSimpleDemoSteps.class);
    private static Comment comment;
    private final static String CONFIG_FILE = "./config.properties";
    private final static String JSON_CONTENT_TYPE = "application/json";
    private static ClientResponse response;
    private static CompositeConfiguration config;

    @Before("@rest")
    public void setup() {
        if (config == null) {
            config = new CompositeConfiguration();
            config.addConfiguration(new SystemConfiguration());
            try {
                config.addConfiguration(new PropertiesConfiguration(CONFIG_FILE));
            } catch (ConfigurationException e) {
                logger.error("Can not load properties file " + CONFIG_FILE);
                e.printStackTrace();
            }

        }
    }

    @When("^Request GET comments, code \"(.*?)\"$")
    public void getRequest(String code) throws Throwable {
//        easy way to make GET request
        response = RestClient.getJSONResponse(config.getString("rest.endpoint") + config.getString("rest.comments"), JSON_CONTENT_TYPE);
        Assert.assertTrue("Expected response code is " + code + ", while real code is " + response.getStatus(), code.equals(Integer.toString(response.getStatus())));
    }

    @When("^Request GET comment by ID, code \"(.*?)\"$")
    public void getRequestCommentById(String code) throws Throwable {
        response = RestClient.getJSONResponse(config.getString("rest.endpoint") + config.getString("rest.comments") + "/101", JSON_CONTENT_TYPE);
        Assert.assertTrue("Expected response code is " + code + ", while real code is " + response.getStatus(), code.equals(Integer.toString(response.getStatus())));
    }

    @When("^Request POST comment, code \"(.*?)\"$")
    public void postRequest(String code) throws Throwable {
//        easy way to make POST request
        response = RestClient.postJSONResponse(config.getString("rest.endpoint") + config.getString("rest.comments"), JSON_CONTENT_TYPE, JsonConverter.getJSONFromObject(comment));
        Assert.assertTrue("Expected response code is " + code + ", while real code is " + response.getStatus(), code.equals(Integer.toString(response.getStatus())));
    }

    @Given("^Generate new name, length \"(.*?)\" for comment template$")
    public void generateNameForCommentTemplate(String length) throws Throwable {
        comment.setName(DataGenerator.generateString(Integer.parseInt(length)));
    }

    @Given("^Use file \"(.*?)\" as comment template$")
    public void useFileAsTemplate(String filename) throws Throwable {
        comment = getCommentBasedOnFile("src/test/resources/templates/" + filename);
        Assert.assertNotNull("Loaded comment template should not be null", comment);
    }

    @When("^Response contains \"(.*?)\" comments$")
    public void response_contains_comments(Integer i) throws Throwable {
//       convert json to List of objects, after that you get ability to do everything you want with json data
        List<Comment> comments = JsonConverter.convertResponseToComments(response);
        Assert.assertTrue("Expected comments number is " + i + ", while real number of comments is " + comments.size(), i.equals(comments.size()));
    }

    @Then("^Compare comment template with response$")
    public void compareTemplateWithResponse() throws Throwable {
        Comment actual_comment = JsonConverter.convertResponseToComment(response);
        Assert.assertTrue("Template and response are not equal", comment.equals(actual_comment));
    }
}
