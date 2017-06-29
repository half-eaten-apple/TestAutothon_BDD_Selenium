
Feature: CapitalCity Rest

 @All @PositiveScenarios @Capital
Scenario Outline: CapitalCity Rest
 
Given initialize test data <scenario>
When user gets Rest connection
And post Rest request
Then get response from the Rest service and validate the result
Then close Rest connection

Examples:
|scenario|
|VerifyCapitalCityScenarioofAssam|

      
  

      