@Runnext
Feature: Testing Saucedemo functionality

  Background:
    Given I am on the saucedemo home page "https://www.saucedemo.com"


  Scenario: Login to saucedemo with Standard_user
    When I login with the following details
      | username      | password     |
      | standard_user | secret_sauce |
    And I click on the login button
    Then "Products" page is displayed


  Scenario: Login to saucedemo with locked_out_user
    When I login with the following details
      | username      | password     |
      | locked_out_user | secret_sauce |
    And I click on the login button
    Then "Sorry, this user has been locked out" error message is displayed

  Scenario: Validate number of products on the products page
    When I login with the following details
      | username      | password     |
      | standard_user | secret_sauce |
    And I click on the login button
    Then the number of items displayed on the Products page is 6
    When I filter by low to high price
    Then The first item has lowest price and the last item has highest price


  Scenario: Add items to basket
    When I login with the following details
      | username      | password     |
      | standard_user | secret_sauce |
    And I click on the login button
    And I add three items to basket
    Then 3 items are shown in the basket
    When I remove one item from the basket
    Then number of items in the basket reduces to 2