package uiactions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.time.Duration;

public class UIActionsPage extends UIInteractionSteps {

    private static final By FILTER = By.cssSelector("button[label='Filters']");
    private static final By APPLY_FILTER = By.cssSelector("button[label='Apply filters']");
    private static final By CLEAR_FILTER = By.cssSelector("button[label='Clear filters']");

    public void clickOnFilter(){
        $(FILTER).click();
    }

    public void clickOnApplyFilter(){
        $(APPLY_FILTER).click();
    }

    public void clickOnClearFilter(){
        $(CLEAR_FILTER).click();
    }



}
