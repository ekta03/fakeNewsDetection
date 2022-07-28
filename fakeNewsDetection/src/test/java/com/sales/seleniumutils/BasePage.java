package com.sales.seleniumutils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.sales.hooks.Hooks;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sales.generalutils.log;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	@SuppressWarnings("unchecked")
	public BasePage(WebDriver driver, Class aClass) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, aClass);
		wait = new WebDriverWait(driver, 30);
	}

	/** get current page title */
	public String getTitle() {
		return driver.getTitle();
	}

	/** get current page url */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/** load page for specified URL */
	public void openApplication(String URL) {
		driver.get(URL);
	}

	/** get current window handle */
	public String getWindow() {
		log.info("getting window handle");
		return driver.getWindowHandle();
	}

	/** get list of open windows */
	public Set<String> getWindows() {
		return driver.getWindowHandles();
	}

	/** switch to specified frame */
	public void switchFrame(WebElement targetFrame) {
		log.info("switching to the frame with id = " + targetFrame);
		driver.switchTo().frame(targetFrame);
	}

	/** switch to window based on handle */
	public void switchWindow(String windowHandle) {
		log.info("switching to the new window");
		driver.switchTo().window(windowHandle);
	}

	/** switch to first frame */
	public void switchDefault() {
		log.info("switching to default page content frame");
		driver.switchTo().defaultContent();
	}

	/** browser back navigation action */
	public void back() {
		log.info("browser back");
		driver.navigate().back();
	}

	/** browser forward navigation action */
	public void forward() {
		log.info("browser forward");
		driver.navigate().forward();
	}

	/** browser page refresh action */
	public void refresh() {
		log.info("browser refresh");
		driver.navigate().refresh();
	}

	/** browser close */
	public void close() {
		log.info("closing Browser");
		driver.quit();
	}

	public void checkPageIsReady(int loopCount) {
		log.info("checking webpage is ready");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
		for (int i = 0; i < loopCount; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void validatePageTitleEquals(String text) {
		Assert.assertTrue("Text Not Found : " + text, driver.getTitle().equalsIgnoreCase(text));
	}

	public void setTimeout(int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public String getText(WebElement element) {
		String textFromWebElement = element.getText();
		log.info("----Getting text from a webelement: " + textFromWebElement);
		return textFromWebElement;
	}

	public String getValue(WebElement element) {
		log.info("getting attribute value from a webelement");
		waitElementLocated(element, 60);
		return element.getAttribute("value");
	}

	public String getDropDownValue(WebElement element) {
		log.info("getting selected option from a drop down");
		waitElementLocated(element, 60);
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public WebElement clear(WebElement element) {
		log.info("clearing field");
		waitElementLocated(element, 60);
		element.clear();
		return element;
	}

	public void enterText(WebElement element, String val) {
		log.info("----Clearing, then sending keys: " + val);
		// scrollToElement(element);
		element.clear();
		// waitElementLocated(element,60);
		element.sendKeys(val);
	}

	public boolean isClickableByExpectedConditions(WebElement webElement, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			log.info("----WebElement is clickable.");
			return true;
		} catch (TimeoutException e) {
			log.error("----Timeout exception after waiting for " + timeOutInSeconds + " seconds.");
			return false;
		} catch (Exception e) {
			log.error("----Exception after waiting for " + timeOutInSeconds + " seconds.");
			return false;
		}
	}

	public void sendKeyBoardKeys(WebElement element, String val) {
		log.info("----Sending keys: " + val);
		waitElementLocated(element, 60);
		element.sendKeys(val);
	}

	public void sendEnter(WebElement element) {
		log.info("----Sending ENTER key.");
		element.sendKeys(Keys.ENTER);
	}

	public void sendTab(WebElement element) {
		log.info("----Sending TAB key.");
		element.sendKeys(Keys.TAB);
	}

	public void sendKeysToInputField(WebElement element, CharSequence... keystosend) throws InterruptedException {
		waitElementLocated(element, 60);
		element.sendKeys(keystosend);
	}

	public void click(WebElement webElement) throws InterruptedException {
		log.info("----Clicking on a webElement");
		waitElementLocated(webElement, 60);
		webElement.click();
	}

	public void click(WebElement element, int waitseconds) {
		log.info("clicking on a element with wait");
		waitElementLocated(element, waitseconds);
		element.click();
	}

	public void selectDropdownByText(WebElement element, String val) {
		log.info("selecting drop down by text");
		Select select = new Select(element);
		select.selectByVisibleText(val);
	}

	public void selectDropdownByValue(WebElement element, String val) throws InterruptedException {
		log.info("selecting drop down by value with custom wait");
		Select select = new Select(element);
		int number_of_options = select.getOptions().size();
		int i = 1;

		while (number_of_options <= 1 && i < 60) {
			Thread.sleep(500);
			i++;
			number_of_options = select.getOptions().size();
		}
		select.selectByValue(val);
	}

	public static int getDropdownCount(WebElement element) {
		log.info("get drop down count");
		Select select = new Select(element);
		List<WebElement> l = select.getOptions();
		return l.size();
	}

	public WebElement getPageBody() {
		return driver.findElement(By.xpath("/html/body"));
	}

	public void performActionsClick(WebElement parentElement, WebElement childElement) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(parentElement).build().perform();
		setSleepTime(1000);
		click(childElement);
	}

	public void moveActionsElement(WebElement parentElement) {
		Actions action = new Actions(driver);
		action.moveToElement(parentElement).build().perform();
	}

	public WebElement getElementByXpath(String xpath, int currentSize) {
		return driver.findElement(By.xpath(xpath + "[" + (currentSize + 1) + "]"));
	}

	public void assertFail(String message) {
		Assert.fail(message);
	}

	public boolean waitElementLocated(WebElement element, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitAllElementsLocated(List<WebElement> elements, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void waitElementLocated(By locator, int timeOutInSeconds) {
		WebElement myDynamicElement = (new WebDriverWait(driver, timeOutInSeconds))
				.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public Boolean waitTextPresentInElement(WebElement element, String val, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.textToBePresentInElement(element, val));

	}

	public boolean isVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return isVisible(locator, wait);
	}

	public boolean isVisible(By locator, WebDriverWait wait) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isInvisible(By locator) {
		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean waitVisibilityofElement(String Val) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Val)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitAvailabilityofElement(List<WebElement> elements, int timeOut) {
		int waitTime = timeOut;
		while (elements.size() == 0 && waitTime != 0) {
			setSleepTime(1000);
			waitTime = waitTime - 1000;
		}
		if (elements.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAttributePresent(WebElement element, String attribute) {
		String value = element.getAttribute(attribute);
		if (value != null) {
			return true;
		}
		return false;
	}

	public Boolean isClickable(By element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEnabled(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element.isEnabled() ? true : false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickElementWithJavaScript(By elementToClick, boolean expectVisible) {
		WebElement element = expectVisible ? wait.until(ExpectedConditions.visibilityOfElementLocated(elementToClick))
				: wait.until(ExpectedConditions.presenceOfElementLocated(elementToClick));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", element);
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void setSleepTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			return;
		}
	}

	public void clickWithJavaScript(By by) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickWithJavaScript(WebElement element) {
		waitElementLocated(element, 90);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void forceClick(WebElement element) {
		if (Hooks.browserName.equals("iexplore")) {
			waitElementLocated(element, 90);
			element.sendKeys(Keys.ENTER);
			setSleepTime(4000);
		} else {
			clickWithJavaScript(element);
			setSleepTime(3000);
		}
	}

	public WebElement convertStringToXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public List<WebElement> convertStringToXpathList(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public void validatePageTextContains(String text) {
		Assert.assertTrue("Text Not Found : " + text, getPageBody().getText().contains(text));
	}

	public void validateElementTextContains(WebElement element, String text) {
		String getText = getText(element).replaceAll("(\\r|\\n)", "");
		boolean isTextFound = getText.contains(text.replaceAll("(\\r|\\n)", ""));
		String assertMessage = "Text Not Found : " + text + ", instead " + getText + " was found.";
		Assert.assertTrue(assertMessage, isTextFound);
	}

	public void validateElementTextEquals(WebElement element, String text) {
		Assert.assertTrue("Text Not Found : " + text, getText(element).equalsIgnoreCase(text));
	}

	public void validateElementValueEquals(WebElement element, String text) {
		Assert.assertTrue("Text Not Found : " + text, getValue(element).equalsIgnoreCase(text));
	}

	public void validateDropDownTextEquals(WebElement element, String text) {
		Assert.assertTrue("Text Not Found : " + text, getDropDownValue(element).equalsIgnoreCase(text));
	}

	public void validateElementVisible(WebElement element, String Fieldname) {
		Assert.assertTrue("Field Not Visible : " + Fieldname, element.isDisplayed());
	}

	public void validateElementEnabled(WebElement element, String Fieldname) {
		Assert.assertTrue("Field Not Enabled : " + Fieldname, element.isEnabled());
	}

	public void validateElementExists(List<WebElement> elements, String Fieldname) {
		Assert.assertTrue("Field Does Not Exist : " + Fieldname, elements.size() > 0);
	}

	public void validateStringsEqual(String expected, String actual, String message) {
		Assert.assertEquals(message, expected, actual);
	}

	public void validateElementDoesNotExist(List<WebElement> elements, String Fieldname) {
		Assert.assertFalse("Field Exist : " + Fieldname, elements.size() > 0);
	}

	public void validateUrlContains(String text) {
		Assert.assertTrue("Url Not contains : " + text, getCurrentUrl().contains(text));
	}

	public void validateElementValueNotNull(WebElement element, String text) {
		Assert.assertNotNull("Value is Null : " + text, getText(element));
	}

	public Map<String, String> getErrorMessages(List<WebElement> errorMessagesElement) {
		Map<String, String> errors = new HashMap<String, String>();
		if (!errorMessagesElement.isEmpty()) {
			List<WebElement> messages = wait.until(ExpectedConditions.visibilityOfAllElements(errorMessagesElement));
			for (WebElement message : messages) {
				errors.put(message.getAttribute("data-qa"), message.getText());
			}
		}
		return errors;
	}

	public void scrollToWebElement(WebElement webElement) {
		log.info("----Scrolling to element " + webElement);
		scrollToWebElement(webElement, -150);
	}

	public void scrollToWebElement(WebElement webElement, int scrollPx) {
		log.info("----Scrolling to webElement " + webElement);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", webElement);
		jse.executeScript("window.scrollBy(0," + scrollPx + ")", "");
	}

	public String getTextOfWebElement(WebElement webElement) {
		String webElementsText = webElement.getText();
		log.info("Getting text from a webElement, text is: " + webElementsText);
		return webElementsText;
	}

	public String getValueOfWebElement(WebElement webElement) {
		waitForVisibilityOfElement(webElement);
		String webElementsAttributeValue = webElement.getAttribute("value");
		log.info("Getting value attribute's content from webElement, value is: " + webElementsAttributeValue);
		return webElementsAttributeValue;
	}

	public boolean clickOnWebElement(WebElement webElement) {
		try {
			log.info("----Trying to click on webElement.");
			webElement.click();
			log.info("----Clicking on webElement was successful.");
			return true;
		} catch (ElementNotVisibleException ex) {
			log.info("----Couldn't click on webElement " + webElement);
			return false;
		}
	}

	public void clickWithJavaScriptOnWebElement(WebElement element) {
		log.info("----Clicking element with JavaScript.");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public boolean hoverOnAndClickOnWebElement(WebElement parentElement, WebElement childElement) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(parentElement).build().perform();
			setSleepTimeInSeconds(1);
			return clickOnWebElement(childElement);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			log.error("Error in...");
			return false;
		}
	}

	public boolean waitForVisibilityOfElement(WebElement element) {
		return waitForVisibilityOfElement(element, 60);
	}

	public boolean waitForVisibilityOfElement(WebElement webElement, int timeOutInSeconds) {
		log.info("----Waiting for visible webElement for " + timeOutInSeconds + " seconds.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			return true;
		} catch (NoSuchElementException ex) {
			log.error("----Error while waiting for visible webElement in " + this.getClass()
					+ ".waitForVisibilityOfElement, NoSuchElementException.");
			ex.printStackTrace();
			return false;
		} catch (TimeoutException e) {
			log.error("----Error while waiting for visible webElement in " + this.getClass()
					+ ".waitForVisibilityOfElement, TimeoutException.");
			return false;
		}
	}

	public boolean waitForInvisibilityOfElement(WebElement webElement) {
		return waitForInvisibilityOfElement(webElement, 0);
	}

	public boolean waitForInvisibilityOfElement(WebElement webElement, int timeOutInSeconds) {
		log.info("----Waiting for invisibility of a webElement.");
		if (timeOutInSeconds > 0) {
			setSleepTimeInSeconds(timeOutInSeconds);
		}
		boolean isInvisible = false;
		try {
			isInvisible = !webElement.isDisplayed();
			log.info("----WebElement is invisible, but present in the html DOM.");
		} catch (NoSuchElementException e) {
			isInvisible = true;
			log.info(
					"----Error while waiting for invisibility of a webElement, NoSuchElementException is caught. WebElement is not present in the html DOM.");
		} catch (StaleElementReferenceException e) {
			isInvisible = true;
			log.info(
					"----Error while waiting for invisibility of a webElement, StaleElementReferenceException is caught.");
		} catch (TimeoutException e) {
			isInvisible = true;
			log.info("----Error while waiting for invisibility of a webElement, TimeoutException is caught.");
		} catch (Exception e) {
			isInvisible = false;
			log.error("----Error while waiting for invisibility of a webElement, " + e.getCause() + " is caught.");
		} finally {
			return isInvisible;
		}
	}

	public boolean waitForPresenceOfElement(By by) {
		return waitForPresenceOfElement(by, 60);
	}

	public boolean waitForPresenceOfElement(By by, int timeOutInSeconds) {
		log.info("----Waiting for presence of a webElement for " + timeOutInSeconds + " seconds.");
		boolean isPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			isPresent = webElement != null;
			log.info("----WebElement is present.");
			return isPresent;
		} catch (Exception ex) {
			log.info("----WebElement is not present.");
			return isPresent;
		}
	}

	public static void setSleepTimeInSeconds(int seconds) {
		try {
			log.info("----Waiting for " + seconds + " seconds.");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.error("----Error while waiting for " + seconds + " seconds.");
		}
	}
}