package pages;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class LoginPage extends TestBase {

	Properties prop = new Properties();

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(getBrowserDriver(), this);
	}

	public void validateLoginPageTitle(String title) {
		assertEquals(getBrowserDriver().getTitle(), title);
	}

	public HomePage login(String login, String pwd) {

		username.sendKeys(login);
		password.sendKeys(pwd);
		loginButton.click();

		return new HomePage();
	}

	
	
	
}
