package com.jeta.jms;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

public class JmsClient implements MessageListener {
    private final static Logger logger = Logger.getLogger(JmsClient.class);

    private static int ackMode;
    private static String clientQueueName;
    private Session session;
    private Destination tempDest;
 
    private boolean transacted = false;
    private MessageProducer producer;
    private Connection connection;

    private int acks;
    static {
        clientQueueName = "client.messages";
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }
 
    public JmsClient() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(transacted, ackMode);
            Destination adminQueue = session.createQueue(clientQueueName);
 
            //Setup a message producer to send message to the queue the server is consuming from
            this.producer = session.createProducer(adminQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
 
            //Create a temporary queue that this client will listen for responses on then create a consumer
            //that consumes message from this temporary queue...for a real application a client should reuse
            //the same temp queue for each message to the server...one temp queue per client
            tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
 
            //This class will handle the messages to the temp queue as well
            responseConsumer.setMessageListener(this);
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
        acks = 0;
    }
 
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
 
    public void onMessage(Message message) {

        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                Assert.assertTrue(messageText.contains("Original text transformed:"));
                logger.info("Received messageText = " + messageText);
                acks++;
            }
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
    }
    public int getAcks() {
        return acks;
    }

    public void sendMessage(String text) throws JMSException {
        //Now create the actual message you want to send
        TextMessage txtMessage = session.createTextMessage();
        txtMessage.setText(text);

        //Set the reply to field to the temp queue you created above, this is the queue the server
        //will respond to
        txtMessage.setJMSReplyTo(tempDest);

        //Set a correlation ID so when you get a response you know which sent message the response is for
        //If there is never more than one outstanding message to the server then the
        //same correlation ID can be used for all the messages...if there is more than one outstanding
        //message to the server you would presumably want to associate the correlation ID with this
        //message somehow...a Map works good
        String correlationId = this.createRandomString();
        txtMessage.setJMSCorrelationID(correlationId);
        this.producer.send(txtMessage);
    }
    
    public static void main(String[] args) {
        new JmsClient();
    }
}