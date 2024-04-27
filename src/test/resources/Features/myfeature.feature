@AutomateApp
Feature: Automation Practice Site

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page
    
 @URLRedirection
  Scenario: User navigate to URL, User redirect to landing page with expected current URL
    When User is on landing page
    Then Validate current URL of landing page with expected URL

  @LandingPageTitle
  Scenario: User naviaget to URL, User redirect to landing page with expected page title
    When User is on landing page
    Then Validate title of landing page with expected title as "Products – Automation Practice Site"
    
@MyAccountLogin
  Scenario Outline: User is able to login into the application
    Given User click on MyAccount from home page
    When User redirected to login page of the application where title us "Products – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on Login button
    Then User successfully login and landing to next page

    Examples: 
      | username               | password       |
      | tom.peter@gmail.com    | Tom.peter@123  |
      
 #@LoginNegative
 # Scenario Outline: User is unable to login into the application
 #   Given User click on My Account page from homepage
 #   When User redirected to the Account page of the application where title is "My Account � Automation Practice Site"
 #   And User enters "<username>" and "<password>" and click on login button
 #   Then User is unable to login with an error message "Error: A user could not be found with this email address."

  #  Examples: 
   #   | username           | password  |
    #  | testUser@gmail.com | user@123  |
     # | testUser1@gmail.com | user@123  |
	@ProductLists
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Android (1)    |
      | HTML (3)       |
      | JavaScript (3) |
      | selenium (1)   |
    And Size of product category shoud be 4

  @DisplayLogo
  Scenario: Logo present on the landing page with specific height and width dimension
    Given User see the logo of the application
    When User fetch the height and width of logo
    Then Height and width of logo should be "50"
		     