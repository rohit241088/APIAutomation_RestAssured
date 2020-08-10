Feature: just demo login feature
Scenario Outline: hitting Add user api valid data
Given I am on reqress website
When I hit api "/api/users" with user as parameter <parameter>
Then I should get response value as <parameterAccepted>
Examples:
|parameter|parameterAccepted|
|2|Janet|
|3|Emma|

