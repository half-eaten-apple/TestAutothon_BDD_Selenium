# Prepared by Author Name
Feature: Calculator Webservice

 @All @PositiveScenarios @Calculator
 Scenario Outline: Calculator Webservice

  Given initialize test data <scenario>
  When user gets webservice connection
  And post webservice request file
  Then get response file and validate the result
  And close webservice connection

Examples:
|scenario|
|AddTwoIntegersWebServiceScenario|
|SubtractTwoIntegersWebServiceScenario|

  

      