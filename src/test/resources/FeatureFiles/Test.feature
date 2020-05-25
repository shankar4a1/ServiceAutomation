@FTSE
Feature: FTSE stock Fetch

  As an API Tester, I'd like to fetch the stock price of some stocks and analyse their value

  Scenario Outline: User Makes a Post request for <Scenario ID>
    Given User has environment setup for <Scenario ID>
    When POST request is updated for FTSE request
    And POST request is triggered for FTSE stocks
    Then User should get Expected results for FTSE
    Examples:
      | Scenario ID |
      | Company_001 |
      | Company_002 |
      | Company_003 |
      | Company_004 |
      | Company_005 |
      | Company_006 |
