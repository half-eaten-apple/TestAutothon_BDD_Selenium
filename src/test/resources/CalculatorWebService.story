Meta:
Scenario : Add Two Integers
Meta:
@All @PositiveScenarios @Calculator
Given initialize test data for webservice scenario <scenario>
When user gets webservice connection
And post webservice request file
Then get response file and validate the result
And close webservice connection

Examples:
|scenario|
|AddTwoIntegersWebServiceScenario|
|SubtractIntegersWebServiceScenario|