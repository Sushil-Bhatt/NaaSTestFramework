package stepDef;

import apis.apiactions.post.PostAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.collaborationportal.NotificationPage;

import static apis.apiactions.post.APIPostForm.NOTIFICATION_MOCK_DATA;
import static org.assertj.core.api.Assertions.assertThat;

public class Notification {
    NotificationPage notificationPage;

    @Steps
    PostAction postAction;

    @And("User is presented with notification icon on the dashboard")
    public void user_is_presented_with_notification_icon_on_the_dashboard() {
        notificationPage.displayNotificationIcon();
    }
    @When("User query the mock data for notification")
    public void user_query_the_mock_data_for_notification() {
        postAction.postRequest_Response(NOTIFICATION_MOCK_DATA);
        postAction.verifyPostSuccess_MockData();
    }

    @Then("User should see a notification pushed popup")
    public void user_should_see_a_notification_pushed_popup() {
        notificationPage.notificationPopUpDisplay();

    }
    @And("User verifies it has a notification with notification count")
    public void user_verifies_it_has_a_notification_with_notification_count() {
        if (notificationPage.notification_count()!=0)
            Serenity.reportThat("Notification Count",
                    ()-> assertThat(notificationPage.notification_count()).isGreaterThan(0));
        Serenity.recordReportData().withTitle("Received Notification Count ").andContents("count is "+notificationPage.notification_count());
    }
    @When("User clicks on notification icon")
    public void user_clicks_on_notification_icon() {
        notificationPage.clickOnNotificationIcon();
    }
    @Then("User should see the notifications")
    public void user_should_see_the_notifications() {
        Serenity.reportThat("Notification Widget items should be equal to notification count in bell icon",
                ()-> assertThat(notificationPage.NotificationWidgetDisplay()).isEqualTo(notificationPage.notification_count()));
        Serenity.recordReportData().withTitle("Unread Notification Count ").andContents("count is "+notificationPage.notification_count());
    }
    @When("User clicks on Mark all as read button")
    public void user_clicks_on_mark_all_as_read_button() {
        notificationPage.clickOnMarkAllReadButton();
    }
    @Then("All notifications should disappear from bell count & notification background color changes")
    public void all_notifications_should_disappear_from_bell_count_notification_background_color_changes() {
        notificationPage.NotificationWidgetColorAfterMarkAll();
        Serenity.reportThat("Notification Count",
                ()-> assertThat(notificationPage.notification_count()).isEqualTo(0));
        Serenity.recordReportData().withTitle("Notification Count ").andContents("count is "+notificationPage.notification_count());
        notificationPage.clickOnNotificationIcon();
    }

}
