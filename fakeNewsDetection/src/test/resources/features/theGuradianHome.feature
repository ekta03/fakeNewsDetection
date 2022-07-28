@Regression
Feature: User Navigates to The Guardian page
Description: As a user i want to extract news details

@sanity
  Scenario: User verifies if news is published by trusted publications
  User stories -
    Given user navigates to url "https://www.theguardian.com/tone/news"
    And gets the title of first article
    Then user goes navigates to url "https://www.google.com/"
    Then user search for article to validate
    Then user press Enter to search
    And verify cites that show similar news

@sanity
  Scenario: User verifies if news is of the correct date
  User stories -
    Given user navigates to url "https://www.theguardian.com/tone/news"
    And gets the title of first article
    Then user goes navigates to url "https://www.google.com/"
    Then user search for article to validate
    Then user press Enter to search
    And verify cites that show similar news
    Then verify dates of news

@sanity
  Scenario: User wants to verify author is trusted
  User stories -
    Given user navigates to url "https://www.theguardian.com/tone/news"
    Given user navigates to TheGuardian url "https://www.theguardian.com/tone/news"
    And user selects the article to check
    Then user verifies if author of article is trusted

@sanity
  Scenario: user verifies images in the news are valid or not
  User stories -
    Given user navigates to url "https://www.theguardian.com/tone/news"
    Given user navigates to TheGuardian url "https://www.theguardian.com/tone/news"
    And user selects the article to check
    And user selects the image to check
    Then user verifies if image of article is trusted

  Scenario: user verifies if claims made are true
  Scenario: user verifies if the content is not misleading
  Scenario: user verifies if quoted sentences are correct

