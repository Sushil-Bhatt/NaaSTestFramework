Feature: Tender Status Component Verification

  Background:
    Given User is on NaaS Dashboard

  Scenario: Verify Tender Status Component Data against API Data
    And User is presented with Tender Status component on the dashboard
    When User Query the Tender Status API for "opportunityStatus" & "totalCount"
    Then User should be able to validate the API data with UI data for opportunityStatus

  Scenario: Notifications Service Verification on UI
    And User is presented with notification icon on the dashboard
    When User query the mock data for notification
    Then User should see a notification pushed popup
    And User verifies it has a notification with notification count
    When User clicks on notification icon
    Then User should see the notifications
    When User clicks on Mark all as read button
    Then All notifications should disappear from bell count & notification background color changes
