@DataTable
Feature: just demo login feature
Scenario: hitting Add user api valid data
Given I am on reqres website
When I hit api "/api/users" with user as parameter
|paramname|value|
|baseURI|https://reqres.in|
|id|2|
Then I should get response value
|paramname|value|
|data.id|2|
|data.first_name|Janet|


