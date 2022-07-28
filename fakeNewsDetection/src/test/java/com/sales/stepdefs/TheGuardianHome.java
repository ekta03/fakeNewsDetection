package com.sales.stepdefs;

import com.sales.hooks.Hooks;
import com.sales.testobjects.TheGuardian_TO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class TheGuardianHome {

	WebDriver driver = Hooks.driver;
	TheGuardian_TO theGuardian_obj = new TheGuardian_TO(driver);


	@Given("^user navigates to url \"([^\"]*)\"$")
	public void navigateTolink(String url){
		theGuardian_obj.navigateTolink(url);
	}

	@And("gets the title of first article")
	public void getTitleOfArticle(){
		theGuardian_obj.getTitleOfArticle();
	}

	@Then("user goes navigates to url \"([^\"]*)\"$")
	public void navigateToUrl(String url){
		theGuardian_obj.navigateToUrl(url);
	}

	@And("user search for article to validate")
	public void enterSearchText(){
		theGuardian_obj.enterSearchText();
	}

	@Then("user press Enter to search")
	public void EnterToSearch() throws InterruptedException {
		theGuardian_obj.EnterToSearch();
	}

	@And("verify cites that show similar news")
	public void verifyCites() throws InterruptedException {
		theGuardian_obj.verifyCites();
	}

	@Then("verify dates of news")
	public void verifyDate() throws ParseException {
		theGuardian_obj.verifyDate();
	}

	@Then("user verifies comments")
	public void verifyComments(){
		theGuardian_obj.verifyComments();
	}

	@Then("user verify Author name")
	public void verifyAuthorName(){
		theGuardian_obj.verifyAuthorName();
	}

	@Given("user navigates to TheGuardian url \"([^\"]*)\"$")
	public void goToUrl(String url) throws InterruptedException {
		theGuardian_obj.goToUrl(url);
	}

	@And("user selects the article to check")
	public void selectArticle() throws InterruptedException {
		theGuardian_obj.selectArticle();
	}

	@Then("user verifies if author of article is trusted")
	public void verifyAuthor(){
		theGuardian_obj.verifyAuthor();
	}

	@And("user selects the image to check")
	public void selectImage(){
		theGuardian_obj.selectImage();
	}

	@Then("user verifies if image of article is trusted")
	public void verifyImage(){
		theGuardian_obj.verifyImage();
	}





//	@Then("user selects the first news of type \"([^\"]*)\"$")
//	public void selectArticle(String type){
//		theGuardian_obj.selectArticle(type);
//	}
//
//	@Then("user reads the title & author of the article")
//	public void navigateToMMT(String url){
//		theGuardian_obj.navigateToUrl(url);
//	}
//
//	@And("user looks for the date of the article")
//	public void navigateToMMT(String url){
//		theGuardian_obj.navigateToUrl(url);
//	}

}