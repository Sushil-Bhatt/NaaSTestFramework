package pages.collaborationportal;

import net.serenitybdd.screenplay.actions.OpenUrl;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/dashboard")
public class TenderStatusPage extends PageObject {
    private static final By TENDER_STATUS_TABLE = By.cssSelector("div[class^=sc-gqgnwQ]");

    public void displayTenderStatusTable(){
        $(TENDER_STATUS_TABLE).isDisplayed();
    }

    public String tenderTableObject(String val){
       return find(By.xpath("//div[contains(@class,'sc-gqgnwQ')]//div//p[text()='"+val+"']//preceding-sibling::p")).getText();
    }

}
