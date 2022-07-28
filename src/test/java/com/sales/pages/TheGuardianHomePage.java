package com.sales.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TheGuardianHomePage {
    @FindAll(@FindBy(xpath = "//li[contains(concat(' ',normalize-space(@class),' '),' u-faux-block-link')]"))
    public static List<WebElement> newsItems;

    @FindBy(xpath = "//div[@data-gu-name='headline']//h1")
    public static WebElement headlineText;

    @FindAll(@FindBy(xpath = "//details[@class='dcr-jeqxnr']/summary"))
    public static List<WebElement> date;

    @FindAll(@FindBy(xpath = "//h3[@class='LC20lb MBeuO DKV0Md']"))
    public static List<WebElement> googleNewsHeader;

    @FindBy(xpath = "//input[@type='text']")
    public static WebElement googleSearchTextBox;

    @FindAll(@FindBy(xpath = "//cite[@class='iUh30 qLRx3b tjvcx']"))
    public static List<WebElement> googleNewsProvider;

    @FindAll(@FindBy(xpath = "//span[@class='MUxGbd wuQ4Ob WZ8Tjf']/span"))
    public static List<WebElement> reportedDates;

    @FindBy(xpath="//a[@rel='author']")
    public static WebElement authorName;

    @FindAll(@FindBy(xpath="//span[@class='js-headline-text']"))
    public static List<WebElement> titleOnGuardian;

    @FindAll(@FindBy(xpath="//a[contains(@class, 'js-headline-text')]"))
    public static List<WebElement> newsBlock;

    @FindBy(xpath = "//button[@title='Yes, I’m happy']")
    public static WebElement acceptCookiesBtn;

    @FindBy(xpath = "//iframe[@title='Iframe title']")
    public static WebElement acceptCookiesFrame;

    @FindBy(xpath = "//img[@class='dcr-4zleql']")
    public static WebElement articleImage;

}
