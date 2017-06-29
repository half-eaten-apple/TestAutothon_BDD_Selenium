
Feature: Feeds Validation

  @All @PositiveScenarios @Feeds
  Scenario Outline: Feeds Validation
    Given initialize test data <scenario>
    When user is logged in and triggers query
    Then download the feeds result file
    And compare inbound and outbound files

    Examples:
      |scenario|
      |FeedsValidationScenario|