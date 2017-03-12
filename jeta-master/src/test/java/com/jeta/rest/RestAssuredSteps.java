package com.jeta.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

public class RestAssuredSteps {

    private static final String CREATE_PATH = "/create";
    private static final String GET_JSON_PATH = "/projects/";
    private static final String JSON_CONTENT_TYPE = "application/json";

    private final File file = new File("src/test/resources/templates/cucumber.json");
    private final String jsonString = new Scanner(file).useDelimiter("\\Z").next();

    private final WireMockServer wireMockServer = new WireMockServer();

    public RestAssuredSteps() throws FileNotFoundException {
    }

    @When("^I upload info about the project$")
    public void usersUploadDataOnAProject() throws IOException {
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo(CREATE_PATH))
                .withHeader("content-type", containing(JSON_CONTENT_TYPE))
                .withRequestBody(containing("testing-framework"))
                .willReturn(aResponse().withHeader("content-type", JSON_CONTENT_TYPE).withStatus(200)));

        assertNotNull(file);
        assertTrue(file.canRead());
        given().header("Content-Type", JSON_CONTENT_TYPE).body(file).expect()
                .contentType(JSON_CONTENT_TYPE).statusCode(200).when()
                .post("http://localhost:8080/create");

        wireMockServer.stop();
    }

    @When("^I want to get information on the (.+) project$")
    public void usersGetInformationOnAProject(String projectName) throws IOException {
        wireMockServer.start();

        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo(GET_JSON_PATH+projectName))
                .willReturn(aResponse().withBody(jsonString).withHeader("content-type", JSON_CONTENT_TYPE).withStatus(200)));

        Response res = RestAssured.get(GET_JSON_PATH+projectName);

        assertEquals(200, res.getStatusCode());
        String json = res.body().asString();
        JsonPath jp = new JsonPath(json);
        assertNotNull(jp);
        assertEquals("cucumber", jp.getString("testing-framework"));
        assertEquals("cucumber.io", jp.getString("website"));
        assertTrue(jp.getString("supported-language").contains("Java"));
        assertTrue(jp.getString("supported-language").contains("C++"));

        /* ANOTHER WAY TO DO THE SAME */
       /* given().contentType(JSON_CONTENT_TYPE).expect()
                .statusCode(200)
                .body(
                        "testing-framework", Matchers.equalTo("cucumber"),
                        "website", Matchers.equalTo("cucumber.io"),
                        "supported-language", Matchers.contains(
                            "Ruby",
                            "Java",
                            "Javascript",
                            "PHP",
                            "Python",
                            "C++"))
                .when()
                .get(GET_JSON_PATH+projectName); */

        wireMockServer.stop();
    }

    @Then("^The server should process it and return a success status$")
    public void theServerShouldReturnASuccessStatus() {
    }

    @Then("^The requested data received$")
    public void theRequestedDataIsReturned() {
    }
}