package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserFactory implements IbasePage {
	
	
	WebDriver driver;
	FileInputStream ip = null;
	Properties prop = new Properties();

	
	public WebDriver createBrowserInstance(String browser) throws MalformedURLException {

		try {
			ip = new FileInputStream("./src/main/java/com/triservicepro/qa/configuration/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		if (browser.equalsIgnoreCase(CHROME)) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			
		} else if (browser.equalsIgnoreCase(FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
//			firefoxOptions.addArguments("--headless");
			driver= new FirefoxDriver(firefoxOptions);
		}
		return driver;
	}

}
