package pages.collaborationportal;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/dashboard")
public class NotificationPage extends PageObject {
    private static final By NOTIFICATION_ICON = By.xpath("//div[@class='sc-2d7bff0-0 hXemDp']//div[@class='notification']/i");
    private static final By NOTIFICATION_COUNT = By.xpath("//div/i[contains(@class,'mi-bell')]//following-sibling::div/span");
    private static final By MARK_ALL_READ_BUTTON = By.cssSelector("button[label='Mark all as read']");
    private static final By NOTIFICATION_POPUP = By.xpath("//div[@class='sc-kNsGIp cjDzku'][text()='A new task has been assigned to RK1']");
    private static final By NOTIFICATION_POPUP_PENDING = By.xpath("//div[@class='sc-kNsGIp cjDzku'][text()='New task - task assignment is asssigned to you']");
    private static final By NOTIFICATION_ITEMS = By.xpath("//div[@class='sc-eThmLp dtVdhh']/div");
    private static final By PENDING_ACTION_ROWS = By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/tbody/tr");
    private static final By PENDING_ACTION_COLUMNS = By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/thead/tr/th");

    private static final By PENDING_ACTION_COLUMN = By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/thead/tr/th//span/span");

    public int notification_count(){
        return Integer.parseInt($(NOTIFICATION_COUNT).getText().trim());
    }

    public void displayNotificationIcon(){
        $(NOTIFICATION_ICON).isDisplayed();
    }

    public void clickOnNotificationIcon(){
        $(NOTIFICATION_ICON).withTimeoutOf(Duration.ofSeconds(20)).waitUntilClickable().click();
    }

    public void clickOnMarkAllReadButton(){
        $(MARK_ALL_READ_BUTTON).click();
    }

    public boolean notificationPopUpDisplay(){
        boolean status = false;
        if($(NOTIFICATION_POPUP).isDisplayed() && $(NOTIFICATION_POPUP_PENDING).isDisplayed()){
            $(NOTIFICATION_POPUP).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();
            $(NOTIFICATION_POPUP_PENDING).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();
            status = true;
        }else if ($(NOTIFICATION_POPUP).isDisplayed() && $(NOTIFICATION_POPUP_PENDING).isDisplayed()) {
            $(NOTIFICATION_POPUP).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();
            $(NOTIFICATION_POPUP_PENDING).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();
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

    public int PendingStatusTableRows(){
        return findAll(PENDING_ACTION_ROWS).size();
    }

    public int PendingStatusTableColumns(){
        return findAll(PENDING_ACTION_COLUMNS).size();
    }

    public List<String> getPendingActionColumns(){
        List<String> filterColumnName = new ArrayList<>();
        for(WebElementFacade webElementFacade:findAll(PENDING_ACTION_COLUMN)){
            filterColumnName.add(webElementFacade.getText());
        }
        return filterColumnName;
    }

    public Map<String,String> dataMapPendingAction(){
        LinkedHashMap<String,String> lmap = new LinkedHashMap<>();
        List<String> dataList = getPendingActionColumns();
        int columns = PendingStatusTableColumns();
        for(int i=1;i==columns;i++){
            for(WebElementFacade elementFacade:findAll(By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/tbody/tr[1]/td["+i+"]/div"))){
                lmap.put(dataList.get(i),elementFacade.getText());
            }
        }
        return lmap;
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

    public boolean verifyPendingActionTableData(List<String> columnNames) throws IOException {
        boolean verifyDataMapping = false;
        String opID = "";
        String custName="";
        String tenID = "";
        List<String> lst = Files.readAllLines(Paths.get("src/main/resources/notificationData.json"));
        for(int i=1;i<lst.size();i++){
            if(lst.get(i).contains("opportunityId")){
                 opID = (lst.get(i).split(":")[1]).split(",")[0];
            } else if (lst.get(i).contains("customerName")) {
                 custName = (lst.get(i).split(":")[1]).split(",")[0];
            }else if (lst.get(i).contains("Tender ID")) {
                  tenID = (lst.get(i).split(":")[1]).split(",")[0];
            }
        }
        for(String key:columnNames) {
            if(key.contains("Action Required")){
                verifyDataMapping =
                        dataMapPendingAction().get("Action Required").trim().equals("task assignment");
            } else if (key.contains("Opportunity ID")) {
                verifyDataMapping =
                        dataMapPendingAction().get("Opportunity ID").trim().equals(opID);
            } else if (key.contains("Customer name")) {
                verifyDataMapping =
                        dataMapPendingAction().get("Customer name").trim().equals(custName);
            } else if (key.contains("Tender ID")) {
                verifyDataMapping =
                        dataMapPendingAction().get("Tender ID").trim().equals(tenID);
            }
        }
        return verifyDataMapping;
    }

}
