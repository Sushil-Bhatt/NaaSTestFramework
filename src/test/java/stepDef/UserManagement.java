package stepDef;

import apis.apiactions.post.PostAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import pages.usermanagement.UserManagementPage;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserManagement {

    @Steps
    PostAction postAction;

    UserManagementPage userManagementPage;

    private List<String> userData;

    private int rowCount=0;
    boolean yFlag= false;
    @Given("User is on NaaS User Management Screen")
    public void user_is_on_naa_s_User_Management_Screen() {
        userManagementPage.open();
        userManagementPage.DisplayUserManagementPage();
        rowCount = userManagementPage.countUserManagementTableRow();
    }

    @Then("User is presented with User Management Filter PopUp")
    public void user_is_presented_with_user_management_filter_pop_up() {
        userManagementPage.DisplayFilterPopUp();
    }

    @When("User input value for {string},{string},{string},{string},{string}, {string}, {string},{string},{string}")
    public void user_input_value_for(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
        userManagementPage.setUserManagementFilterValue(string,string2,string3,string4,string5,string6,string7,string8,string9);
    }

    @Then("User should be able to verify filter data for {string},{string},{string},{string},{string}, {string}, {string},{string},{string}")
    public void user_should_be_able_to_verify_filter_data_for(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
        List<String> columnNames = userManagementPage.getUserManagementColumns();
        Serenity.reportThat("Filter Data validation on UI",
                ()-> assertThat(userManagementPage.verifyFilteredDataOnUI(columnNames,string,string2)).isTrue());
    }

    @Given("Filtered data gets cleared")
    public void Filtered_data_gets_cleared() {
        Serenity.reportThat("Clear Filter- Filtered data gets cleared",
                ()-> assertThat(userManagementPage.countUserManagementTableRow()).isEqualTo(rowCount));
        userManagementPage.closeFilter();
    }

    @When("User clicks on Edit link for a particular {string}")
    public void user_clicks_on_edit_link_for_a_particular(String string) {
        userManagementPage.clickOnEditLinkForUser(string);
    }
    @Then("User is presented with edit user popup")
    public void user_is_presented_with_edit_user_popup() {
        userManagementPage.DisplayUpdateUserPopUp();
    }
    @Then("User verifies non editable fields in the popup {string},{string},{string},{string},{string},{string}")
    public void user_verifies_non_editable_fields_in_the_popup(String s1,String s2,String s3,String s4,String s5,String s6) {
        Serenity.reportThat("User verifies non editable fields in the Edit Popup",
                ()-> assertThat(userManagementPage.verifyNonEditableFields(s1,s2,s3,s4,s5,s6)).isTrue());
    }

    @Then("User makes update to the editable fields {string},{string},{string},{string}")
    public void user_makes_update_to_the_editable_fields(String s1, String s2, String s3, String s4, io.cucumber.datatable.DataTable dataTable) throws AWTException {
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
        userData = Arrays.asList(data.get(0).get(s1),data.get(0).get(s2),data.get(0).get(s3),data.get(0).get(s4));
        userManagementPage.setEditableFieldValues(s1,s2,s3,s4,userData);
    }

    @Then("User clicks on Update details button")
    public void user_clicks_on_update_details_button() {
        userManagementPage.clickUpdateDetails();
    }
    @Then("Changes should reflect on UI for fields {string},{string},{string},{string}")
    public void changes_should_reflect_on_ui_for_fields(String s1,String s2,String s3,String s4) {
        Serenity.reportThat("Data verified - Edit user detail displayed on UI",
                ()-> assertThat(userManagementPage.verifyEditPopupData(s1,s2,s3,s4,userData)).isTrue());
    }

    @When("User clicks on close button")
    public void user_clicks_on_close_button() {
        userManagementPage.clickCloseEditPopUp();
    }
    @Then("Edit popup should disappear")
    public void edit_popup_should_disappear() {
        Serenity.reportThat("Edit user popup disappeared",
                ()-> assertThat(userManagementPage.editUserPopupNotPresent()).isTrue());
    }

    @When("User query User Management page API to fetch data for {string} & {string}")
    public void user_query_user_management_page_api_to_fetch_data_for(String maesrkUid, String roleName) {
        postAction.postRequest_Response_userManagementScreen();
        yFlag = userManagementPage.verifyUserRoleData(maesrkUid,roleName);

    }
    @Then("User should be able to validate API data with UI data on User Management page")
    public void user_should_be_able_to_validate_api_data_with_ui_data_on_user_management_page() {
        Serenity.reportThat("Users Table - Verified API Data against UI Data",
                ()-> assertThat(yFlag).isTrue());
    }


}
