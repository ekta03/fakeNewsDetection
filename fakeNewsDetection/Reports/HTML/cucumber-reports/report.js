$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("theGuradianHome.feature");
formatter.feature({
  "line": 2,
  "name": "User Navigates to The Guardian page",
  "description": "Description: As a user i want to extract news details",
  "id": "user-navigates-to-the-guardian-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Regression"
    }
  ]
});
formatter.before({
  "duration": 8598235541,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "user verifies images in the news are valid or not",
  "description": "User stories -",
  "id": "user-navigates-to-the-guardian-page;user-verifies-images-in-the-news-are-valid-or-not",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 34,
      "name": "@sanity"
    }
  ]
});
formatter.step({
  "line": 37,
  "name": "user navigates to url \"https://www.theguardian.com/tone/news\"",
  "keyword": "Given "
});
formatter.step({
  "line": 38,
  "name": "user navigates to TheGuardian url \"https://www.theguardian.com/tone/news\"",
  "keyword": "Given "
});
formatter.step({
  "line": 39,
  "name": "user selects the article to check",
  "keyword": "And "
});
formatter.step({
  "line": 40,
  "name": "user selects the image to check",
  "keyword": "And "
});
formatter.step({
  "line": 41,
  "name": "user verifies if image of article is trusted",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.theguardian.com/tone/news",
      "offset": 23
    }
  ],
  "location": "TheGuardianHome.navigateTolink(String)"
});
formatter.result({
  "duration": 3559631917,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.theguardian.com/tone/news",
      "offset": 35
    }
  ],
  "location": "TheGuardianHome.goToUrl(String)"
});
formatter.result({
  "duration": 9690872000,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.selectArticle()"
});
formatter.result({
  "duration": 4592962709,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.selectImage()"
});
formatter.result({
  "duration": 82180083,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.verifyImage()"
});
formatter.result({
  "duration": 1822677792,
  "error_message": "java.lang.AssertionError: Image is not trusted, no similar images found\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.assertTrue(Assert.java:41)\n\tat com.sales.testobjects.TheGuardian_TO.verifyImage(TheGuardian_TO.java:167)\n\tat com.sales.stepdefs.TheGuardianHome.verifyImage(TheGuardianHome.java:85)\n\tat ✽.Then user verifies if image of article is trusted(theGuradianHome.feature:41)\n",
  "status": "failed"
});
formatter.after({
  "duration": 530034583,
  "status": "passed"
});
});