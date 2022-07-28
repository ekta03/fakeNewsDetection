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
  "duration": 13968906541,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "User verifies if news is published by trusted publications",
  "description": "User stories -",
  "id": "user-navigates-to-the-guardian-page;user-verifies-if-news-is-published-by-trusted-publications",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@sanity"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "user navigates to url \"https://www.theguardian.com/tone/news\"",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "gets the title of first article",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "user goes navigates to url \"https://www.google.com/\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "user search for article to validate",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "user press Enter to search",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "verify cites that show similar news",
  "keyword": "And "
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
  "duration": 4668665917,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.getTitleOfArticle()"
});
formatter.result({
  "duration": 89125,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.google.com/",
      "offset": 28
    }
  ],
  "location": "TheGuardianHome.navigateToUrl(String)"
});
formatter.result({
  "duration": 1677809458,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.enterSearchText()"
});
formatter.result({
  "duration": 2462365333,
  "status": "passed"
});
formatter.match({
  "location": "TheGuardianHome.EnterToSearch()"
});
