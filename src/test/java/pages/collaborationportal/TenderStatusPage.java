package pages.collaborationportal;

import net.serenitybdd.screenplay.actions.OpenUrl;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/dashboard")
public class TenderStatusPage extends PageObject {
    private static final By TENDER_STATUS_TABLE = By.cssSelector("div[class^=sc-gqgnwQ]");

    private static final By DASHBOARD_PAGE = By.xpath("//p[contains(text(),'Welcome,')]");

    public void displayDashBoardPage(){
        $(DASHBOARD_PAGE).isDisplayed();
    }

    public void displayTenderStatusTable(){
        $(TENDER_STATUS_TABLE).isDisplayed();
    }

    public String tenderTableObject(String val){
       return find(By.xpath("//div[contains(@class,'sc-gqgnwQ')]//div//p[text()='"+val+"']//preceding-sibling::p")).getText();
    }

}
