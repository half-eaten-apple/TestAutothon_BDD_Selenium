Feature: Speaker Program
    Speaker Program Functionality for E2E flow

  @All @PositiveScenarios @NegativeScenario
  Scenario: Create Interaction with missing Mandatory Fields
    Given initialize test data NegativeScenarios
    When login salesforce
    Then user should successfully logged in
    And create interaction & verify error message for missing mandatory fields

  # Starting Next Scenario
  @All @PositiveScenarios @SpeakerProgram
  Scenario Outline: Create Speaker Program
    Given initialize test data <scenario>
    When login salesforce
    Then user should successfully logged in
    And create new interaction
    Then check interaction is created
    And add new product topic
    Then product topics should be added
    And add new location
    Then location should be added
    And add new participant
    Then participant should be added
    And add new logistics
    Then logistics should be added
    And add new estimate
    Then estimate should be added
    And change interaction status to accepted
    And confirm location
    And change interaction status to set-up complete
    Then interaction status should be close-out required
    And pay expenses
    And add disclosure agreement
    Then interaction status should be complete

    Examples:
      |scenario|
      |SpeakerProgram_AZ|










