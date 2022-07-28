package com.sales.hooks;

import java.io.File;
import java.io.IOException;

import com.sales.generalutils.JsonParsing;
import com.sales.generalutils.log;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import com.sales.seleniumutils.Screenshot;
import com.google.gson.internal.LinkedTreeMap;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;
	public static String targetURL;
	public static int width;
	public static int height;
	public static String viewPort;
	public static String browserName;
	Scenario scenario;

	// ----------Before Hook-----------------------------//
	static
	{
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	public String baseUrl = "https://google.com";

	@Before
	public void beforestartUp(Scenario scenario) throws IOException {
			System.out.println("launching Chrome browser");
			System.setProperty("webdriver.chrome.driver", "../de-sales-selenium/Browsers/chromedriver");
			driver = new ChromeDriver();
			driver.get(baseUrl);
		   	log.startTestScenario(scenario.getName());
		   	this.scenario = scenario;
	}

	// -----------After Hook-----------------------------//
	@After
	public void after(Scenario scenario) throws IOException, InterruptedException {
		log.endTestScenario(scenario.getName());
		if (scenario.isFailed()) {
			Screenshot.grabScreenshotForReport(driver,scenario);
		}
		driver.quit();
	}
}