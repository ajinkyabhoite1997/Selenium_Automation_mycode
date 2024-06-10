import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestBase  {
	
	HomePage homePage;
	
	@Parameters({"username","password"})
	@Test(priority=1)
	public void login(String username, String password) throws InterruptedException {
		
		new LoginPage().login(username, password);
		Thread.sleep(2000);
	
	}
}
