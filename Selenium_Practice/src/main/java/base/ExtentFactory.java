package base;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	
private ExtentFactory() {
		
	}
	
	private static ExtentFactory instance  = new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
	
	
	//factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	
	public void removeExtentObject() {
		extent.remove();
	}
	

}
