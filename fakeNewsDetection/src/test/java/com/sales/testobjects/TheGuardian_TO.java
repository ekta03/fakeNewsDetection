package com.sales.testobjects;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.sales.generalutils.log;
import com.sales.pages.TheGuardianHomePage;
import com.sales.seleniumutils.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.jayway.restassured.RestAssured.given;
import static com.sales.generalutils.log.info;

public class TheGuardian_TO extends BasePage {
    String firstArticle;
    String articleImage;
    String trustedCites[] = {"vice.com", "abc.net", "gov.au", "indianexpress" , "bbc.com" , "sports.yahoo"};
    String nonTrustedCites[] = {"xyz"};
    String author[] = {"Ellie Violet Bramley","Peter Walker","xyz","pqr","ask"};
    public TheGuardian_TO(WebDriver driver) {
        super(driver, TheGuardianHomePage.class);
    }

    public void navigateTolink(String url) {
        RestAssured.baseURI = "https://content.guardianapis.com";
        String title = given().when().get("https://content.guardianapis.com/search?api-key=e271422a-5214-427f-b186-dcb7f14ed25e&type=article").then().extract()
                .path("response.results.webTitle[2]");
        firstArticle = title.trim();
        log.info("First Article : " + firstArticle);
    }

    public void getTitleOfArticle(){
        String firstArticleToVerify = firstArticle;
    }

    public void navigateToUrl(String url){
        openApplication(url);
        String title = driver.getTitle();
        log.info("Title is : " + title);
        Assert.assertTrue("user is navigated to google.com" , title.contains("Google"));
    }

    public void enterSearchText(){
        TheGuardianHomePage.googleSearchTextBox.sendKeys(firstArticle);
    }

    public void EnterToSearch() throws InterruptedException {
        sendEnter(TheGuardianHomePage.googleSearchTextBox);
    }

    public void verifyCites() throws InterruptedException {
        boolean flag = false;
        int trustedCitesCount = 0;
        int nonTrustedCitesCount = 0;
        for(int i=0;i<TheGuardianHomePage.googleNewsProvider.size();i++) {
            for(int j=0;j<trustedCites.length;j++){
                if(TheGuardianHomePage.googleNewsProvider.get(i).getText().contains(trustedCites[j])){
                    trustedCitesCount++;
                }
                else {
                    nonTrustedCitesCount++;
                }
            }
        }
        log.info("News is reported by " + trustedCitesCount + " trustedCites and " + nonTrustedCitesCount + " nonTrusted cites");
    }

    public void verifyDate() throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        LocalDate now = LocalDate.now();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        log.info("DATE CLASS " + formatter.format(date));
        LocalDate threeDaysBefore = now.minusDays(3);
        log.info(dtf.format(threeDaysBefore));

        for (int i=0;i<TheGuardianHomePage.reportedDates.size();i++) {
            try {
                Date dateOfNews = new SimpleDateFormat("dd/MMM/yyyy").parse(TheGuardianHomePage.reportedDates.get(i).getText());
                formatter.format(dateOfNews);
                if (dateOfNews.after(date)){
                    log.info("Date is correct");
                }
                else{
                    log.info("Date is outside range");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void verifyComments(){
        for(int i=0;i<TheGuardianHomePage.googleNewsProvider.size();i++){
            if(TheGuardianHomePage.googleNewsProvider.get(i).getText().contains("bbc.com")){
                TheGuardianHomePage.googleNewsProvider.get(i).click();
            }
        }
    }

    public void verifyAuthorName(){
        for(int i=0;i<TheGuardianHomePage.googleNewsHeader.size();i++){
            if(TheGuardianHomePage.googleNewsHeader.get(i).getText().equals("firstArticle")){
                TheGuardianHomePage.googleNewsHeader.get(i).click();
                List<String> nameList = new ArrayList<>(Arrays.asList(author));
                log.info(TheGuardianHomePage.authorName.getText());
                Assert.assertTrue("Author is valid Author", nameList.contains(TheGuardianHomePage.authorName.getText()));
            }
        }
    }

    public void goToUrl(String url) throws InterruptedException {
        openApplication(url);
        waitElementLocated(TheGuardianHomePage.acceptCookiesFrame,10000);
        switchFrame(TheGuardianHomePage.acceptCookiesFrame);
        TheGuardianHomePage.acceptCookiesBtn.click();
    }

    public void selectArticle() throws InterruptedException {
        switchDefault();
        for(int i=0;i<TheGuardianHomePage.titleOnGuardian.size();i++){
            //log.info(TheGuardianHomePage.titleOnGuardian.get(i).getText());
            if(TheGuardianHomePage.titleOnGuardian.get(i).getText().contains(firstArticle)){
                log.info("Article found");
                TheGuardianHomePage.newsBlock.get(i).click();
                break;
            }
        }
    }

    public void verifyAuthor(){
        List<String> nameList = new ArrayList<>(Arrays.asList(author));
        log.info(TheGuardianHomePage.authorName.getText());
        Assert.assertTrue("Author is not valid Author", nameList.contains(TheGuardianHomePage.authorName.getText()));
    }


    public void selectImage(){
        articleImage = TheGuardianHomePage.articleImage.getAttribute("src");
        log.info(articleImage);
    }

    public void verifyImage(){
        String engine = "google_reverse_image";
        String serpApi_Key="6dacd13699a9a48b11bf5e12348d06dee47d55fb2204bca50c3ceb75e63e6cac";
        String url = "https://serpapi.com/search?engine="+engine+"&api_key="+serpApi_Key+"&image_url="+articleImage;
        Response res = RestAssured.given().get(url);
        ResponseBody b = res.getBody();
        String responseBody = b.asString();
        //log.info(responseBody);
        Assert.assertTrue("Image is not trusted, no similar images found", !responseBody.contains("Google Reverse Image hasn't returned any results for this query"));
    }
}
