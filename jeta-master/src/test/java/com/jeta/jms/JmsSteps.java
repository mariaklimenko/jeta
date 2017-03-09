package com.jeta.jms;

import activemq.ServerSide;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Random;

public class JmsSteps {
    private JmsClient client;
    private ServerSide server;

    @Given("^Server side is started$")
    public void server_is_started() throws Throwable {
        server = new ServerSide();
    }

    @When("^I send (\\d+) text messages to the queue$")
    public void i_send_msg_to_queue(int messages) throws Throwable {
        client = new JmsClient();
        for (int i=0; i<messages; i++){
            client.sendMessage(createRandomString());
        }
     }

    @Then("^I get (\\d+) modified text messages back within (\\d+) ms$")
    public void i_get_modified_msg_back(int messages, int timeout) throws Throwable {
        Thread.sleep(timeout);
        Assert.assertTrue("Expected number of correct messages: " + messages + " while real number is " + client.getAcks(), client.getAcks() == messages);
    }

    private static String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
}