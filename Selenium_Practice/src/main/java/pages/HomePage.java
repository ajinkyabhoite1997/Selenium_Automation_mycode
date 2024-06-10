package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class HomePage extends TestBase { 
	

	private WebDriver driver;
	
	@FindBy( css = "div#head-nav ul.user-nav li.profile_menu a span" )
	@CacheLookup
	WebElement userNameLabel;

	public HomePage() {
		PageFactory.initElements(driver, this );
	}

	public void verifyHomePageTitle( String title ) {
		assertEquals( driver.getTitle(), title );
	}

	public void verifyUserNameOnTopBar( String userName ) {
		assertEquals( userName, userNameLabel.getText()  );
	}
	
	

}
