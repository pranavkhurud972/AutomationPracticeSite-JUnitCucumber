package StepDef;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Core.WebDriverFactory;
import PageObject.CommonPageObjects;
import PageObject.HeaderSectionObjects;
import PageObject.LoginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdef {
	
	private static final Logger logger = LogManager.getLogger(stepdef.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "https://practice.automationtesting.in/shop/";
	
	//============== Declare object reference of pageObjects classes =============================//
	CommonPageObjects cmnPageObject;
	HeaderSectionObjects headerObject;
	LoginPageObjects loginPageObject;
	
	//====================== Before Hook =========================================================//	
		@Before
		public void setUp(Scenario scn)//Parameterize Scn
		{
			this.scn=scn;

			//Get the desired browser to be invoked
			String browserName= WebDriverFactory.getBrowserName();
			driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
			scn.log("Browser get invoked");
			//Initialize object of page objects classes 
			cmnPageObject = new CommonPageObjects(driver,scn);
			headerObject = new HeaderSectionObjects(driver,scn);
			loginPageObject = new LoginPageObjects(driver,scn);
			
		}
		
	//====================== After Hook =========================================================//
		@After(order=2)
		//Capture screenshot if test case get failed
		public void captureScreenshot(Scenario scn)
		{
			if(scn.isFailed())
			{
				TakesScreenshot srnshot= ((TakesScreenshot)driver);
				byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
				scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
				scn.log("Attach a screenshot as step get failed");
			}
			else
			{
				scn.log("Test case get passed, no screenshot is captured");
			}
		}
		
		@After(order=1)
		//Quit the browser
		public void tearDown(Scenario scn)
		{
			WebDriverFactory.quitTheBrowser();
			scn.log("Browser is quit");
		}
		
	
	
			


	
		@Given("User navigate to URL and open the landing page")
		public void user_navigate_to_url_and_open_the_landing_page() {
			WebDriverFactory.navigateToURL(base_url);
		}

		@When("User is on landing page")
		public void user_is_on_landing_page() {
			logger.info("User is on landing page after navigating to base URL");
		    scn.log("User is on landing page after navigating to base URL");
		}
		@Then("Validate current URL of landing page with expected URL")
		public void validate_current_url_of_landing_page_with_expected_url() {
			 cmnPageObject.validatePageURL();
			 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
		}
		
		@Then("Validate title of landing page with expected title as {string}")
		public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
			cmnPageObject.validatePageTitle(titleOfPage);
			scn.log("Validate page title is: "+ driver.getTitle());
		}
	

		@Given("User click on MyAccount from home page")
		public void user_click_on_my_account_from_home_page() {
			WebElement  myaccount= driver.findElement(By.xpath("//a[normalize-space()='My Account']"));
			myaccount.click();
		}
		@When("User redirected to login page of the application where title us {string}")
		public void user_redirected_to_login_page_of_the_application_where_title_us(String loginPageTitle) throws InterruptedException 
		{
			String titleOfLoginpage = driver.getTitle();
			Assert.assertEquals(loginPageTitle, titleOfLoginpage);
			Thread.sleep(5000);
			
		}
		@When("User enters {string} and {string} and click on Login button")
		public void user_enters_and_and_click_on_login_button(String username, String password) {
			WebElement emailIdInputFieldElement = driver.findElement(By.xpath("//input[@id='username']"));
			emailIdInputFieldElement.sendKeys(username);
			WebElement passwordInputFieldElement = driver.findElement(By.xpath("//input[@id='password']"));
			passwordInputFieldElement.sendKeys(password);
			WebElement loginInButtonElement = driver.findElement(By.xpath("//input[@name='login']"));
			loginInButtonElement.click();
		  
		}
		@Then("User successfully login and landing to next page")
		public void user_successfully_login_and_landing_to_next_page() {
		   
		}



}
