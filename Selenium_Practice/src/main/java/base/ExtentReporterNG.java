package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	
static ExtentReports extent;
	
	
	public static ExtentReports setupExtentReport() throws Exception {
		FileInputStream ip = null;
		Properties prop = new Properties();
		try {
			ip = new FileInputStream("./src/main/java/com/Selenium_Practice/src/main/java/configuration/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date(0, 0, 0); 
		String actualDate = format.format(date);
		
		String reportPath = System.getProperty("user.dir")+
				"/reports/ExecutionReport_"+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("DocumentTitle");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("ReportName");
		
		extent.setSystemInfo("Executed on Environment: ", prop.getProperty("url"));
		extent.setSystemInfo("Executed on Browser: ", prop.getProperty("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));

		return extent;
	}

	
	
}
