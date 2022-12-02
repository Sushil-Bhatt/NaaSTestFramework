package pages.collaborationportal;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/dashboard")
public class NotificationPage extends PageObject {

    private static final By NOTIFICATION_ICON = By.xpath("//div/i[contains(@class,'mi-bell')]");
    private static final By NOTIFICATION_COUNT = By.xpath("//div/i[contains(@class,'mi-bell')]//following-sibling::div/span");
    private static final By MARK_ALL_READ_BUTTON = By.cssSelector("button[label='Mark all as read']");

    public int notification_count(){
        return Integer.parseInt($(NOTIFICATION_COUNT).getText().trim());
    }

    public void displayNotificationIcon(){
        $(NOTIFICATION_ICON).isDisplayed();
    }

    public void clickOnNotificationIcon(){
        $(NOTIFICATION_ICON).click();
    }

    public void clickOnMarkAllReadButton(){
        $(MARK_ALL_READ_BUTTON).click();
    }

}
