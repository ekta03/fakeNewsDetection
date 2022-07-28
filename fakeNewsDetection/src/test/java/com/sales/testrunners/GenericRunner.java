package com.sales.testrunners;

import org.apache.log4j.BasicConfigurator;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.sales.stepdefs", "com.sales.hooks"}, plugin = { "pretty", "html:Reports/HTML/cucumber-reports"}, tags = "@sanity" , monochrome = true)
public class GenericRunner {
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		JUnitCore.main("com.sales.testrunners.GenericRunner");
	}
}