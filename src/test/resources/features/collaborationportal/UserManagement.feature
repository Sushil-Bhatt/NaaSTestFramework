Feature: User Management Screen Verification
  This feature is to test various components on the User Management Screen

  Background:
    Given User is on NaaS User Management Screen

  Scenario Outline: Apply & Clear Filter on User Management Screen
    When User clicks on Filter Button
    Then User is presented with User Management Filter PopUp
    When User input value for "<userName>","<role>","<areaCode>","<regionCode>","<countryCode>", "<concernCode>", "<concernName>","<valueProposition>","<vertical>"
    And Click on Apply Filters
    Then User should be able to verify filter data for "<userName>","<role>","<areaCode>","<regionCode>","<countryCode>", "<concernCode>", "<concernName>","<valueProposition>","<vertical>"
    When User clicks on Filter Button
    When User clicks on Clear Filter Button
    Then Filtered data gets cleared
    Examples:
      |userName|role|areaCode|regionCode|countryCode|concernCode|concernName|valueProposition|vertical|
      |SDP986|CONTRACT_PRODUCT_MANAGER||||||||
      |SHA012|INTERMODAL_PRODUCT_MANAGER||||||||
      |VSO040|INTERMODAL_PRODUCT_MANAGER||||||||

  Scenario: Edit User Detail
    When User clicks on Edit link for a particular "SDP986"
    Then User is presented with edit user popup
    And User verifies non editable fields in the popup "Unique ID","User name","Email address","Role","Manager name","Manager email address"
    And User makes update to the editable fields "Customer Name","Concern Code","Vertical","Customer Code"
    |Customer Name|Concern Code|Vertical|Customer Code|
    |  India      | India      |India   |India        |
    And User clicks on Update details button
    Then Changes should reflect on UI for fields "Customer Name","Concern Code","Vertical","Customer Code"

  Scenario: Close Edit Popup
    When User clicks on Edit link for a particular "SDP986"
    Then User is presented with edit user popup
    When User clicks on close button
    Then Edit popup should disappear

  Scenario: Verify User Management screen grid data
    When User query User Management page API to fetch data for "Unique ID" & "Role"
    Then User should be able to validate API data with UI data on User Management page
