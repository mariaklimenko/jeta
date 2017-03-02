package com.jeta.rest;

import com.jeta.json.JsonConverter;
import com.sun.jersey.api.client.ClientResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import com.jeta.rest.entity.Comment;

import java.util.List;

/**
 * Created by yksenofontov on 01.03.2017.
 */
public class RestSteps {
    private static ClientResponse response;

    @When("^Get request, url = \"(.*?)\", code \"(.*?)\"$")
    public void getRequest(String url, String code) throws Throwable {
//        easy way to make GET request
        response = RestClient.getResponse(url,"application/json");
        Assert.assertTrue("Expected response code is " + code + ", while real code is " + response.getStatus(), code.equals(Integer.toString(response.getStatus())));
    }

    @Then("^Response contains \"(.*?)\" comments$")
    public void response_contains_comments(Integer i) throws Throwable {
//       convert json to List of objects, after that you get ability to do everything you want with json data
        List<Comment> comments = JsonConverter.convertResponseToComments(response);
        Assert.assertTrue("Expected comments number is " + i + ", while real number of comments is " + comments.size(), i.equals(comments.size()));
    }

}
