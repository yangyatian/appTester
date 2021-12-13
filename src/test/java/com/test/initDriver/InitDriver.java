package com.test.initDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class InitDriver {
	
	protected AndroidDriver<WebElement> driver;
	
	String appPackage = "com.biyingkeji.funchat";
	String appActivity = "com.biyingkeji.funchat.ui.system.login.LaunchActivity";
	String udid = "VBJ4C18519016748";
	
	String yamlfile = "D:\\tools\\eclipse_luna\\workspace\\appTester\\src\\test\\java\\com\\test\\initDriver\\capability.yaml";

	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		//apk path
		/*
		 * File clasPathRoot = new File(System.getProperty("user.dir")); File
		 * appDir = new File(clasPathRoot, "apps"); File app = new File(appDir,
		 * "app-test-1.0.0.apk");
		 */
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "HWCOL");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("platformName", "Android");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("sessionOverride", true); 
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}

}
