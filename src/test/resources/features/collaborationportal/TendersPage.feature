Feature: Tenders Page Filter Verification
  This feature is to test various components on Tenders Page

  Background:
    Given User is on NaaS Tenders Page

  Scenario Outline: Verify Tender Page Filter Data value against filter fields API Data
    When User clicks on Filter Button
    Then User is presented with the Filter PopUp
    And User Query "<seacrhField>" API to fetch data
    Then User click on "<seacrhField>" UI to enter data "<searchValue>" to validate it against API Data
    Examples:
      |seacrhField|searchValue|
      |tenderid  |   T1       |
      |opportunityid| OT      |
      |opportunityowner| Jacob|
      |customer|   HERO       |

  Scenario Outline: Apply & Clear Filter on Tenders Page
    When User clicks on Filter Button
    Then User is presented with the Filter PopUp
    When User input value for "<tenderid>","<opportunityid>","<opportunityowner>","<customer>", "<status>"
    And Click on Apply Filters
    Then User should be able to verify filter data for "<tenderid>","<opportunityid>","<opportunityowner>","<customer>", "<status>"
    When User clicks on Clear Filter Button
    Then User should see the data accordingly
  Examples:
    |tenderid|opportunityid|opportunityowner|customer|status|
    | T-113       | OT-123573         |   Lamo  |   HINO MOTORS LTD||
    | T-104       | OT-123562|   Lam   |  HINO MOTORS LTD ||
    | T-112       |  OT-223572           |   Lamo  |   HINO MOTORS LTD     |Completed|

  Scenario: Tender Page data Display on UI
    When User query tenders page API to fetch data for "tenderId" & "customerCode"
    Then User should be able to validate API data with UI data