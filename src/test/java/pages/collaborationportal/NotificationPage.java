package pages.collaborationportal;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/dashboard")
public class NotificationPage extends PageObject {

    private static final By NOTIFICATION_ICON = By.xpath("//div/i[contains(@class,'mi-bell')]");
    private static final By NOTIFICATION_COUNT = By.xpath("//div/i[contains(@class,'mi-bell')]//following-sibling::div/span");
    private static final By MARK_ALL_READ_BUTTON = By.cssSelector("button[label='Mark all as read']");

    private static final By NOTIFICATION_POPUP = By.xpath("//div[@class='Toastify__toast-body']//div[contains(@class,'cjDzku')]");

    private static final By NOTIFICATION_ITEMS = By.xpath("//div[@class='sc-eThmLp dtVdhh']/div");

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

    public boolean notificationPopUpDisplay(){
        boolean status = false;
        if($(NOTIFICATION_POPUP).getText().trim().contains("A new task has been assigned to"))
            status = true;
        else if ($(NOTIFICATION_POPUP).getText().trim().contains("Opportunity Qualified")) {
            status = true;
        }
        return status;
    }

    public int NotificationWidgetDisplay(){
        int count = 0;
        for (WebElementFacade webElementFacade:findAll(NOTIFICATION_ITEMS)){
            if(webElementFacade.getAttribute("style").trim().contains("background: rgba(181, 224, 245, 0.2)")){
                count++;
            }
        }
        return count;
    }

    public boolean NotificationWidgetColorAfterMarkAll(){
        boolean flag = false;
        for (WebElementFacade webElementFacade:findAll(NOTIFICATION_ITEMS)){
            if(webElementFacade.getAttribute("style").trim().contains("background: rgb(255, 255, 255)")){
                flag = true;
            }
        }
        return flag;
    }

}
