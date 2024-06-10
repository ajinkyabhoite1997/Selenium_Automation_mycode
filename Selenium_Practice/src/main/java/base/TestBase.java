package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	BrowserFactory bf = new BrowserFactory();
	FileInputStream ip = null;
	Properties prop = new Properties();
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private WebDriver driver;
	
	

	@BeforeMethod
	public void LaunchApplication() throws Exception {

		try {
			ip = new FileInputStream("zconfiguration/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browser = prop.getProperty("browser").toLowerCase().trim();
		String url = prop.getProperty("url");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(url);

	}

	public WebDriver getBrowserDriver() { 
		return DriverFactory.getInstance().getDriver();
	}
	
	@Parameters({ "url", "browser" })
	@BeforeTest
	public void setup(String url, String browser) {

		System.out.println("running test on : " + browser);
		
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions co = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("plz pass the right browser...." + browser);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

}
