package com.test.util;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import com.test.initDriver.InitDriver;

public class Action extends InitDriver {

	public Action(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}

	// 向上滑动
	public static void swipeToUp(AndroidDriver<WebElement> driver, long dur) {
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		new TouchAction(driver)
				.press(PointOption.point(width / 2, 8 * height / 10))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(dur)))
				.moveTo(PointOption.point(width / 2, height / 5)).release()
				.perform();
	}

	// 向下滑动
	public static void swipeToDown(AndroidDriver<WebElement> driver, long dur) {
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		new TouchAction(driver).press(PointOption.point(width / 2, height / 5))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(dur)))
				.moveTo(PointOption.point(width / 2, 8 * height / 10))
				.release().perform();
	}

	// 向左滑动
	public static void swipeToLeft(AndroidDriver<WebElement> driver, long dur) {
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		new TouchAction(driver).press(PointOption.point(8 * width/ 10, height / 2))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(dur)))
				.moveTo(PointOption.point(width / 10, height / 2))
				.release().perform();
	}
	
	// 向右滑动  使用方法：class.swipeToRight(driver,500L);
		public static void swipeToRight(AndroidDriver<WebElement> driver, long dur) {
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			new TouchAction(driver).press(PointOption.point(8 * width/ 10, height / 2))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(dur)))
					.moveTo(PointOption.point(8 * width / 10, height / 2))
					.release().perform();
		}

	// 点击返回
	public void clickBack2() throws InterruptedException, IOException {
		String cmdstr = "adb shell input keyevent 4";
		Runtime.getRuntime().exec(cmdstr).waitFor();
		Thread.sleep(10000);
	}

	// 截图
	public String getScreen() {
		String fileRoute = "//testing/testPicture/";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String picname = fileRoute + df.format(new Date()).toString() + ".png";
		File screen = this.driver.getScreenshotAs(OutputType.FILE);
		System.out.println(picname);
		File screenFile = new File(picname);
		try {
			FileUtils.copyFile(screen, screenFile);
			String time = df.format(new Date()).toString();
			System.out.println("time：" + time);
			return time;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 获取h5
	 */
	public void switchToWeb() {
		try {
			Set<String> contextNames = this.driver.getContextHandles();
			for (String contextName : contextNames) {

				if (contextName.contains("WEBVIEW")
						|| contextName.contains("webview")) {
					this.driver.context(contextName);
					System.out.println("seitchtoWeb");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// id存在并点击
	public void clickIfExitId(String id) {
		try {
			driver.findElementById(id).click();
		} catch (NoSuchElementException e) {
			System.out.println("Can not find element with id " + id
					+ " . Skip!");
		}
	}

	// 查找id并点击
	public void clickIfExitIds(String[] ids) {
		for (String id : ids) {
			clickIfExitId(id);
		}
	}

}
