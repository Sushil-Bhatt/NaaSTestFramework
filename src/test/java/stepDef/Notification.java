package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.collaborationportal.NotificationPage;

public class Notification {
    @Steps
    NotificationPage notificationPage;
    @And("User is presented with notification icon on the dashboard")
    public void user_is_presented_with_notification_icon_on_the_dashboard() {
        notificationPage.displayNotificationIcon();
    }
    @And("User verifies it has a notification with notification count")
    public void user_verifies_it_has_a_notification_with_notification_count() {
        if (notificationPage.notification_count()!=0)
            Serenity.recordReportData().withTitle("Notification Count").andContents("count is "+notificationPage.notification_count());
    }
    @When("User clicks on notification icon")
    public void user_clicks_on_notification_icon() {
        notificationPage.clickOnNotificationIcon();
    }
    @Then("User should see the notifications")
    public void user_should_see_the_notifications() {

    }
    @When("User clicks on Mark all as read button")
    public void user_clicks_on_mark_all_as_read_button() {
        notificationPage.clickOnMarkAllReadButton();
    }
    @Then("All notifications should disappear")
    public void all_notifications_should_disappear() {
        Serenity.recordReportData().withTitle("Notification Count").andContents("count is "+notificationPage.notification_count());
        notificationPage.clickOnNotificationIcon();
    }

}
