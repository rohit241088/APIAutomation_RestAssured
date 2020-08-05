Feature: just demo login feature
Scenario: hitting Add user api valid data
Given User is on home page
When hit  search "get" api with "/api/users" 
Then Response should contains "morpheus" and "leader"

