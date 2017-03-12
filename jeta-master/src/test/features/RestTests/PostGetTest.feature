@rest
Feature: POST, GET example
# 1. Post request with body based on template
# 2. Get request, convert body to object
# 3. compare template with created object
  Scenario: generate test data
    Given Use file "comment.json" as comment template

  Scenario: create new comment
    Then Request POST comment, code "201"

  Scenario: get comment and compare objects
    When Request GET comment by ID, code "200"
    Then Compare comment template with response