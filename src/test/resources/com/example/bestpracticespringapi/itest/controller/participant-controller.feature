Feature: Participant Controller Test
  Scenario: Get All Participant Successfully
    When I send a "GET" request to "/api/participants"
    Then I get a response code 200


  Scenario: Create Participant
    When I POST this json request to "/api/participants"
    """
    {
      "name" : "test",
      "age" : 120
    }
    """
    Then I get a response code 201
