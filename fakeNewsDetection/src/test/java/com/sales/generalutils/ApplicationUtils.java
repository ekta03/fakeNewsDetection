package com.sales.generalutils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ApplicationUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

	/**
	 * Waits for the loading indicator (spinning wheel) to stop showing.
	 * 
	 * @return true if loading indicator disappeared before timeout
	 */
	public boolean waitForLoading() {
		try {
			Boolean loadingIndicatorGone = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".icon-loading")));
			return loadingIndicatorGone;
		} catch (TimeoutException e) {
			log.info("Timeout when waiting for page content to load.");
			return false;
		}
	}

	/**
	 * Looks for a certain element after waiting for the loading indicator to
	 * stop showing.
	 * 
	 * @param selector
	 *            selector for finding the element
	 * @return true if element is found
	 */
	public boolean elementGetsLoaded(By selector) {
		if (waitForLoading()) {
			List<WebElement> elements = driver.findElements(selector);
			return !elements.isEmpty();
		} else {
			log.error("Page loading didn't finish before timeout.");
			return false;
		}
	}
}
