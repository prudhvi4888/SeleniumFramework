
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background: 
 Given I Landed on ecommerce page

  @Regression
  Scenario Outline: Submit the order Positive condition
    Given I want to login with <name> and <password>
    When I add product <productName> to the cart
    And Checkout<productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  						| password 		| productName  		|
      | anshika@gmail.com | Iamking@000 | ZARA COAT 3 		|
      | shetty@gmail.com  | Iamking@000 | ADIDAS ORIGINAL	|
