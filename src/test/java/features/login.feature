Feature: just demo login feature
Scenario: hitting Add user api valid data
Given User is on home page
When hit  search "GET" api with "/search" and parametertype "q" and parameterValue "rohit"
Then Response should contains "rohit"

