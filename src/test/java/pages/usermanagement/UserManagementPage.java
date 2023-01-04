package pages.usermanagement;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.*;
import java.util.List;

@DefaultUrl("https://naas-shell-ui.dev.maersk-digital.net/user-management")
public class UserManagementPage extends PageObject {

    private static final By USER_MANAGEMENT_TABLE_COLUMN = By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/thead/tr/th//span");
    private static final By USER_MANAGEMENT_COLUMNS_ROWS = By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/tbody/tr");

    private static final By CLOSE_EDIT_POPUP = By.cssSelector("button[aria-label='Close'][type='button']");
    private static final By FILTER_WINDOW = By.cssSelector("div[class='filter__header']");
    private static final By UPDATE_USER_POPUP = By.xpath("//h2[text()='Update user']");
    private static final By USER_NAME = By.cssSelector("input[placeholder='Search user name or UID']");
    private static final By ROLE = By.cssSelector("input[placeholder='Search a role']");
    private static final By AREA_CODE = By.cssSelector("input[placeholder='Search area code']");
    private static final By REGION_CODE = By.cssSelector("input[placeholder='Search region code");
    private static final By COUNTRY_CODE = By.cssSelector("input[placeholder='Search country code']");
    private static final By CONCERN_CODE = By.cssSelector("input[placeholder='Search concern code']");
    private static final By CONCERN_NAME = By.cssSelector("input[placeholder='Search concern name']");
    private static final By VALUE_PROPOSITION = By.cssSelector("input[id='valueProposition']");
    private static final By VERTICAL = By.cssSelector("input[placeholder='Search vertical']");
    private static final By CLOSE_FILTER = By.xpath("//div/button[@class='sc-lmyTeu cSvrJC']");
    private static final By USERS_PAGE = By.xpath("//p[text()='Users']");
    private static final By EDIT_POPUP_DATA_GRID = By.xpath("//div[@class='attribute-container']/p[1]");
    private static final By EDIT_DATA_VALUE_GRID_CUSTOMER_NAME = By.xpath("//div[@class='attribute-container']/p[text()='customerName']/following-sibling::p");
    private static final By EDIT_DATA_VALUE_GRID_CUSTOMER_CODE = By.xpath("//div[@class='attribute-container']/p[text()='customerCode']/following-sibling::p");
    private static final By EDIT_DATA_VALUE_GRID_CONCERN_CODE = By.xpath("//div[@class='attribute-container']/p[text()='concernCode']/following-sibling::p");
    private static final By EDIT_DATA_VALUE_GRID_VERTICAL = By.xpath("//div[@class='attribute-container']/p[text()='vertical']/following-sibling::p");
    private static final By UPDATE_DETAIL_BUTTON = By.xpath("//button/span[text()='Update details']");
    private static final By CUSTOMER_NAME_DETAIL = By.xpath("//div[text()='Select or search customer name']/parent::div/parent::div//input");

    private static final By CONCERN_CODE_DETAIL = By.xpath("//div[text()='Select or search concern code']/parent::div/parent::div//input");

    private static final By VERTICAL_DETAIL = By.xpath("//div[text()='Select or search vertical']/parent::div/parent::div//input");

    private static final By CUSTOMER_CODE_DETAIL = By.xpath("//div[text()='Select or search customer code']/parent::div/parent::div//input");

    public void DisplayFilterPopUp(){
        $(FILTER_WINDOW).isDisplayed();
    }

    public void clickUpdateDetails(){
        $(UPDATE_DETAIL_BUTTON).click();
    }

    public void clickCloseEditPopUp(){
        $(CLOSE_EDIT_POPUP).click();
    }

    public void DisplayUpdateUserPopUp(){
        $(UPDATE_USER_POPUP).isDisplayed();
    }

    public boolean editUserPopupNotPresent(){
        return !($(UPDATE_USER_POPUP).isVisible());
    }

    public boolean nonEditableFields(String obj){
        return find(By.xpath("//label[text()='"+obj+"']/parent::div/following-sibling::div/input")).isDisabled();
    }

    public void editableFieldValues(String obj, List<String> lst) throws AWTException {
        Robot robot = new Robot();
        find(By.xpath("//div[text()='"+obj+"']/parent::div/following-sibling::div//div[contains(@aria-label,'Remove')]")).
                withTimeoutOf(Duration.ofSeconds(20)).waitUntilClickable().click();
        if(obj.equals("Customer Name")){
            $(CUSTOMER_NAME_DETAIL).type(lst.get(0));
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } else if (obj.equals("Concern Code")) {
            $(CONCERN_CODE_DETAIL).type(lst.get(1));
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } else if (obj.equals("Vertical")) {
            $(VERTICAL_DETAIL).type(lst.get(2));
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }else if (obj.equals("Customer Code")) {
            $(CUSTOMER_CODE_DETAIL).type(lst.get(3));
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    public void setEditableFieldValues(String s1,String s2,String s3,String s4,List<String> values) throws AWTException {
        editableFieldValues(s1,values);
        editableFieldValues(s2,values);
        editableFieldValues(s3,values);
        editableFieldValues(s4,values);
    }
    
    public boolean verifyNonEditableFields(String s1,String s2,String s3,String s4,String s5,String s6){
        boolean sflag = true;
        sflag = nonEditableFields(s1);
        sflag = nonEditableFields(s2);
        sflag = nonEditableFields(s3);
        sflag = nonEditableFields(s4);
        sflag = nonEditableFields(s5);
        sflag = nonEditableFields(s6);
        return sflag;
    }

    public void DisplayUserManagementPage(){
        $(USERS_PAGE).isDisplayed();
    }

    public void closeFilter(){
        try{
            $(CLOSE_FILTER).click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void selectSearchItem(String object){
        withTimeoutOf(Duration.ofSeconds(30)).
                find(By.xpath("//span[text()='"+object+"']/parent::label/div")).
                click();
    }

    public int countUserManagementTableRow(){
        return findAll(USER_MANAGEMENT_COLUMNS_ROWS).size();
    }

    public void setUserManagementFilterValue(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9){
        if(!s1.equals("")){
            $(USER_NAME).click();
            $(USER_NAME).sendKeys(s1);
            selectSearchItem(s1);
        }
        if (!s2.equals("")) {
            $(ROLE).click();
            $(ROLE).sendKeys(s2);
            selectSearchItem(s2);
        }
        if (!s3.equals("")) {
            $(AREA_CODE).click();
            $(AREA_CODE).sendKeys(s3);
            selectSearchItem(s3);
        }
        if (!s4.equals("")) {
            $(REGION_CODE).click();
            $(REGION_CODE).sendKeys(s4);
            selectSearchItem(s4);
        }
        if (!s5.equals("")) {
            $(COUNTRY_CODE).click();
            $(COUNTRY_CODE).sendKeys(s5);
            selectSearchItem(s5);
        }
        if (!s6.equals("")) {
            $(CONCERN_CODE).click();
            $(CONCERN_CODE).sendKeys(s6);
            selectSearchItem(s6);
        }
        if (!s7.equals("")) {
            $(CONCERN_NAME).click();
            $(CONCERN_NAME).sendKeys(s7);
            selectSearchItem(s7);
        }
        if (!s8.equals("")) {
            $(VALUE_PROPOSITION).click();
            $(VALUE_PROPOSITION).sendKeys(s8);
            selectSearchItem(s8);
        }
        if (!s9.equals("")) {
            $(VERTICAL).click();
            $(VERTICAL).sendKeys(s9);
            selectSearchItem(s9);
        }
    }

    public List<String> getUserManagementColumns(){
        List<String> filterColumnName = new ArrayList<>();
        for(WebElementFacade webElementFacade:findAll(USER_MANAGEMENT_TABLE_COLUMN)){
            filterColumnName.add(webElementFacade.getText());
        }
        return filterColumnName;
    }

    public int UsersTableColumns(){
        return findAll(USER_MANAGEMENT_COLUMNS_ROWS).size();
    }

    public List<String> UserDataOnUI(String id){
        List<String> usersonUI = new ArrayList<>();
        List<String> columnNames = getUserManagementColumns();
        int col_user = (columnNames.indexOf(id))+1;
        for(int i=0;i<=countUserManagementTableRow();i++){
            for(WebElementFacade elementFacade:findAll(By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/tbody/tr["+i+"]/td["+col_user+"]"))){
                usersonUI.add(elementFacade.getText());
            }
        }
        return usersonUI;
    }

    public boolean verifyUserRoleData(String s1, String s2){
        boolean yflag = false;
        List<String> userdataonUI = UserDataOnUI(s1);
        Collections.sort(userdataonUI);
        List<String> roledataonUI = UserDataOnUI(s2);
        Collections.sort(roledataonUI);
        List<String> userdataonAPI = compareUserMapAPI_UI(s1);
        Collections.sort(userdataonAPI);
        List<String> roledataonAPI = compareRoleMapAPI_UI(s2);
        Collections.sort(roledataonAPI);

        boolean userData = userdataonUI.equals(userdataonAPI);
        boolean roleData = roledataonUI.equals(roledataonAPI);
        if(userData && roleData){
            yflag = true;
        }
        return yflag;
    }

    public Map<String,List<String>> getTableDataMapping(){
        List<String> columnNames = getUserManagementColumns();
        return UserManagementTableData(columnNames);
    }

    public Map<String,List<String>> UserManagementTableData(List<String> lst){
        Map<String,List<String>> newDataMap = new LinkedHashMap<>();
        for(int i=1;i<=findAll(USER_MANAGEMENT_COLUMNS_ROWS).size();i++){
            int k=0;
            for(WebElementFacade elementFacade:findAll(By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/tbody/tr["+i+"]/td"))){
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


    public boolean verifyEditPopupData(String s1, String s2, String s3, String s4, List<String> lst){
        boolean flag = false;
        for(WebElementFacade webElementFacade:findAll(EDIT_POPUP_DATA_GRID)){
            if(webElementFacade.getText().equalsIgnoreCase(s1.replaceAll("\\s",""))){
                if($(EDIT_DATA_VALUE_GRID_CUSTOMER_NAME).getText().equals(lst.get(0))){
                    flag = true;
                }
            }else if(webElementFacade.getText().equalsIgnoreCase(s2.replaceAll("\\s",""))){
                if($(EDIT_DATA_VALUE_GRID_CONCERN_CODE).getText().equals(lst.get(1))){
                    flag = true;
                }
            }else if(webElementFacade.getText().equalsIgnoreCase(s3.replaceAll("\\s",""))){
                if($(EDIT_DATA_VALUE_GRID_VERTICAL).getText().equals(lst.get(2))){
                    flag = true;
                }
            }else if(webElementFacade.getText().equalsIgnoreCase(s4.replaceAll("\\s",""))){
                if($(EDIT_DATA_VALUE_GRID_CUSTOMER_CODE).getText().equals(lst.get(3))){
                    flag = true;
                }
            }
        }
        return flag;
    }

    public boolean verifyFilteredDataOnUI(List<String> columnNames,String s1,String s2){
        boolean verifyDataMapping = false;
        for(String key:columnNames) {
            if (key.contains("Unique ID")) {
                for (String st : getTableDataMapping().get("Unique ID")) {
                    if (st.equals(s1)) {
                        verifyDataMapping = true;
                    }
                }
            } else if (key.contains("Role")) {
                for (String st : getTableDataMapping().get("Role")) {
                    if (st.equals(s2)) {
                        verifyDataMapping = true;
                    }
                }
            }
        }
        return verifyDataMapping;
    }

    public int columnNumberForUserOnEditEntry(String columnName){
        int j=1;
        List<String> columnNames = getUserManagementColumns();
        for(int i=1;i<=columnNames.size();i++){
            if(columnNames.get(i).equals(columnName)){
                j= i+1;
                break;
            }
        }
        return j;
    }

    public void clickOnEditLinkForUser(String user){
        int k = columnNumberForUserOnEditEntry("Unique ID");
        int l = columnNumberForUserOnEditEntry("Actions");

        for(int i=1;i<=findAll(USER_MANAGEMENT_COLUMNS_ROWS).size();i++) {
            if ($(By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/tbody/tr["+i+"]/td["+k+"]")).getText().equals(user)){
                $(By.xpath("//table[@class='sc-ksbECg iLwcuG table-wrapper']/tbody/tr["+i+"]/td["+l+"]")).click();
                break;
            }
        }
    }

    public List<String> compareUserMapAPI_UI(String s1) {
        List<Map<String,String>> userMap = SerenityRest.lastResponse().getBody().jsonPath().get("user");
        List<String> uid = new ArrayList<>();
        for(int i=0;i<userMap.size();i++){
            if(s1.equals("Unique ID")){
                uid.add(userMap.get(i).get("maerskUid"));
            }
        }
        return uid;
    }

    public List<String> compareRoleMapAPI_UI(String s2) {
        List<Map<String,String>> roleMap = SerenityRest.lastResponse().getBody().jsonPath().get("role");
        List<String> role = new ArrayList<>();
        for(int i=0;i<roleMap.size();i++){
            if(s2.equals("Role")){
                role.add(roleMap.get(i).get("roleName"));
            }
        }
        return role;
    }
}
