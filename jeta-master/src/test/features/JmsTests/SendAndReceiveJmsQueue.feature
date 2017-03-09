@JmsTests
Feature: Send and Receive JMS messages

  # This test can be launched with or without running ActiveMQ server (in second option test will be launched in embedded broker only)
  # Learn how to locally run ActiveMQ JMS server from: http://activemq.apache.org/getting-started.html#GettingStarted-WindowsBinaryInstallation
  Scenario: Send 6 Text Messages to the Queue
    Given Server side is started
    When I send 6 text messages to the queue
    Then I get 6 modified text messages back within 1000 ms
