
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @NegativeScenario
  Scenario Outline: Login Negative Scenario
    Given I Landed on ecommerce page
    When  I want to login with <name> and <password>
    Then "Incorrect email or password." message is displayed

 Examples: 
      | name  						| password  | 
      | anshika@gmail.com | Iamking@0 | 
      | shetty@gmail.com  | Iamking@0 | 