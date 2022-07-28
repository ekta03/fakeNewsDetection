package com.sales.seleniumutils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;

public class Screenshot {
	public static void grabScreenshotForReport(WebDriver driver, Scenario scenario) {
		try {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File("/Users/ektakriplani/Downloads/de-sales-selenium/image/png/" + scenario.getName()+".png");
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File saveScreenshot(WebDriver driver, String screen, String filePath) {
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.moveFile(scrFile, new File(filePath + screen + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scrFile;
	}
}