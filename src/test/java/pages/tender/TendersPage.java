package pages.tender;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import uiactions.UIActionsPage;

import java.time.Duration;
import java.util.*;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/tender")
public class TendersPage extends PageObject {
    private static final By TENDER_ID = By.cssSelector("input[placeholder='Search tender ID']");
    private static final By OPPORTUNITY_ID = By.cssSelector("input[placeholder='Search opportunity ID']");
    private static final By OPPORTUNITY_OWNER = By.cssSelector("input[placeholder='Search opportunity owner']");
    private static final By CUSTOMER = By.cssSelector("input[placeholder='Search customer name or code']");
    private static final By FILTER_WINDOW = By.cssSelector("div[class='sc-eThmLp sc-62897a75-3  jxDYJl']");
    private static final By SEARCH_LIST_ITEMS = By.xpath("//div[contains(@class,'bHfSbl')]//div[contains(@class,'edkrvK hcGPTs')]/div//span");

    private static final By TENDERS_COUNT = By.xpath("//span[contains(text(),'Showing 10')]");
    private static final By TENDER_ID_BUTTON = By.xpath("//div[@class='sc-fkxeQW fwktNk']/span[text()='Tender ID']/ancestor::button");
    private static final By OPPORTUNITY_ID_BUTTON = By.xpath("//div[@class='sc-fkxeQW fwktNk']/span[text()='Opportunity ID']/ancestor::button");
    private static final By OPPORTUNITY_OWNER_BUTTON = By.xpath("//div[@class='sc-fkxeQW fwktNk']/span[text()='Opportunity owner']/ancestor::button");
    private static final By CUSTOMER_BUTTON = By.xpath("//div[@class='sc-fkxeQW fwktNk']/span[text()='Customer']/ancestor::button");
    private static final By FILTER_COLUMNS = By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/thead/tr/th//span");

    private static final By FILTER_COLUMNS_ROWS = By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/tbody/tr");

    private static final By TENDERS_HEADER = By.xpath("//h4[text()='Tenders']");


    public void clickOnStatus(String statusName){
        if (find(By.xpath("//div[contains(@class,'bHfSbl')]//span[text()='"+statusName+"']")).getText().trim().equals(statusName)){
            find(By.xpath("//div[contains(@class,'bHfSbl')]//span[text()='"+statusName+"']/parent::label/div")).click();
        }
    }

    public void isDisplayedTendersPage(){
        $(TENDERS_HEADER).isDisplayed();
    }

    public void clickOnOppId(){
        $(OPPORTUNITY_ID).click();
    }
    public void clickOnTenId(){
        $(TENDER_ID).click();
    }
    public void clickOnOppOwner(){
        $(OPPORTUNITY_OWNER).click();
    }
    public void clickOnCustomer(){
        $(CUSTOMER).click();
    }
    public boolean DisplayFilterPopUp(){
        return $(FILTER_WINDOW).isDisplayed();
    }

    public boolean DisplayTotalCount(){
        return $(TENDERS_COUNT).isDisplayed();
    }

    public String getTotalCountOnUI(){
        return $(TENDERS_COUNT).getText();
    }

    public void selectSearchItem(String object){
        withTimeoutOf(Duration.ofSeconds(30)).
                find(By.xpath("//div[@class='sc-NjEqx bHfSbl']//span[text()='"+object+"']/parent::label/div")).
                click();
    }

    public void setSearchValue(String uiField,String searchValue){
        if(Objects.equals(uiField, "tenderid")){
            clickOnTenId();
            $(TENDER_ID).sendKeys(searchValue);
        } else if (Objects.equals(uiField, "opportunityid")) {
            clickOnOppId();
            $(OPPORTUNITY_ID).sendKeys(searchValue);
        } else if (Objects.equals(uiField, "opportunityowner")) {
            clickOnOppOwner();
            $(OPPORTUNITY_OWNER).sendKeys(searchValue);
        } else if (Objects.equals(uiField, "customer")) {
            clickOnCustomer();
            $(CUSTOMER).sendKeys(searchValue);
        }
    }

    public void setSearchField(String tenderid,String opportunityid,String opportunityowner, String customer, String status) {
        String[] strStatusArray = { "In progress", "won", "Completed","Lost","Draft"};
        String[] strCustomerArray = { "HERO MOTORS", "HINO MOTORS LTD", "ABC MOTORS LT","ABC MOTORS LTD 1"};
        List<String> sttus = Arrays.asList(strStatusArray);
        List<String> cusst = Arrays.asList(strCustomerArray);

        if(tenderid!=null && tenderid.contains("T")) {
            clickOnTenId();
            $(TENDER_ID).sendKeys(tenderid);
            selectSearchItem(tenderid);
            $(TENDER_ID_BUTTON).click();
        }
        if (opportunityid!=null && (opportunityid.contains("OP") || opportunityid.contains("OT"))) {
            clickOnOppId();
            $(OPPORTUNITY_ID).sendKeys(opportunityid);
            selectSearchItem(opportunityid);
            $(OPPORTUNITY_ID_BUTTON).click();
        }
        if (opportunityowner!=null) {
            clickOnOppOwner();
            $(OPPORTUNITY_OWNER).sendKeys(opportunityowner);
            selectSearchItem(opportunityowner);
            $(OPPORTUNITY_OWNER_BUTTON).click();
        }
        if (customer!=null && cusst.contains(customer)){
            clickOnCustomer();
            $(CUSTOMER).sendKeys(customer);
            selectSearchItem(customer);
            $(CUSTOMER_BUTTON).click();
        }
        if (status!=null && sttus.contains(status)) {
            clickOnStatus(status);
        }
    }

    public void compareUI_API_SearchData(List<String> searchitemsAPI){
        boolean flag = false;
        for(String s: searchitemsAPI){
            for (WebElementFacade webElementFacade:findAll(SEARCH_LIST_ITEMS)){
                if (s.contains(webElementFacade.getText())) {
                    flag = true;
                }
            }
        }
    }

    public List<String> getFilterColumns(){
        List<String> filterColumnName = new ArrayList<>();
        for(WebElementFacade webElementFacade:findAll(FILTER_COLUMNS)){
            filterColumnName.add(webElementFacade.getText());
        }
        return filterColumnName;
    }

    public Map<String,List<String>> tableData(List<String> lst){
        Map<String,List<String>> newDataMap = new LinkedHashMap<>();
        for(int i=1;i<=findAll(FILTER_COLUMNS_ROWS).size();i++){
            int k=0;
            for(WebElementFacade elementFacade:findAll(By.xpath("//table[@class='sc-cTUoWY cZwoVQ table-wrapper']/tbody/tr["+i+"]/td/div"))){
                if(newDataMap.containsKey(lst.get(k))){
                    newDataMap.get(lst.get(k)).add(elementFacade.getText());
                }else{
                    List<String> memberList = Arrays.asList(elementFacade.getText());
                    memberList = new ArrayList<>(memberList);
                    newDataMap.put(lst.get(k), memberList);
                }
                k++;
                if(k==lst.size()){
                    break;
                }
            }
        }
        return newDataMap;
    }

    public Map<String,List<String>> getTableDataMapping(){
        List<String> columnNames = getFilterColumns();
        return tableData(columnNames);
    }

    public boolean verifyFilterDataOnUI(List<String> columnNames,String string,String string2,String string3,String string4,String string5){
        boolean verifyDataMapping = false;
        for(String key:columnNames){
            if(key.contains("Tender ID")){
                for (String st:getTableDataMapping().get("Tender ID")){
                        if(st.equals(string)){
                            verifyDataMapping = true;
                    }
                }
            }else if(key.contains("Opportunity ID")){
                for (String st:getTableDataMapping().get("Opportunity ID")){
                    if(st.equals(string2)){
                        verifyDataMapping = true;
                    }
                }
            } else if (key.contains("Opportunity Owner")) {
                for (String st:getTableDataMapping().get("Opportunity Owner")){
                    if(st.equals(string3)){
                        verifyDataMapping = true;
                    }
                }
            } else if (key.contains("Customer")) {
                for (String st:getTableDataMapping().get("Customer")){
                    if(st.equals(string4)){
                        verifyDataMapping = true;
                    }
                }
            } else if (key.contains("Status")) {
                for (String st:getTableDataMapping().get("Status")){
                    if(st!=null){
                        if(st.equals(string5)){
                            verifyDataMapping = true;
                        }
                    }
                }
            }}
        return verifyDataMapping;
    }
}
