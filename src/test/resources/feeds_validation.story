Meta:
!-- Authored by Vikas
Scenario : Feeds Validation
Meta:
@All @PositiveScenarios @Feeds
Given initialize test data <scenario>
When user is logged in and triggers query
Then download the feeds result file
And compare inbound and outbound files

Examples:
|scenario|
|FeedsValidationScenario|