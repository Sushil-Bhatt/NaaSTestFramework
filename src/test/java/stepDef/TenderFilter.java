package stepDef;

import apis.apiactions.CommonActions;
import apis.apiactions.get.GetAction;
import apis.apiactions.post.PostAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import pages.tender.TendersPage;
import uiactions.UIActionsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static apis.apiactions.post.APIPostForm.NOTIFICATION_MOCK_DATA;
import static apis.apiactions.post.APIPostForm.TENDERS_API_DATA;
import static org.assertj.core.api.Assertions.assertThat;

public class TenderFilter {

    private static List<String> searchValues= new ArrayList<>();
    TendersPage tendersPage;
    private static List<String> opportunities = new ArrayList<>();
    private static List<String> opportunitiesValue = new ArrayList<>();
    private static String totalRecordCount = "";

    @Steps
    GetAction getAction;
    @Steps
    PostAction postAction;
    @Steps
    CommonActions commonActions;

    @Steps
    UIActionsPage uiActionsPage;

    @Given("User is on NaaS Tenders Page")
    public void user_is_on_naa_s_tenders_page() {
        tendersPage.open();
        tendersPage.isDisplayedTendersPage();
    }
    @When("User query tenders page API to fetch data for {string} & {string}")
    public void user_query_tenders_page_api_to_fetch_data_for(String s1, String s2) {
        postAction.postRequest_Response(TENDERS_API_DATA);
        totalRecordCount = SerenityRest.lastResponse().getBody().jsonPath().get("totalElements");
        List<Map<String,String>> tenderStatusObject = getAction.readResponseData_TenderStatus(s1,s2,"opportunity");
        opportunities = commonActions.ObjectStateKeys(tenderStatusObject);
    }
    @Then("User should be able to validate API data with UI data")
    public void user_should_be_able_to_validate_api_data_with_ui_data() {
        boolean sFlag = false;
        for(int i=0;i<opportunities.size();i++) {
            if(tendersPage.getTableDataMapping().get("Tender ID").size()==opportunities.size()){
                for(String str:tendersPage.getTableDataMapping().get("Tender ID")){
                    if(opportunities.contains(str)){
                        sFlag = true;
                    }else {
                        sFlag = false;
                    }
                }
                if(sFlag){
                    Serenity.reportThat("Comparing UI data against API data for opportunity ID",
                            ()-> assertThat(tendersPage.getTableDataMapping().get("Tender ID")).isEqualTo(opportunities));
                }
            }
        }
    }
    @When("User clicks on Filter Button")
    public void user_clicks_on_filter_button() {
        uiActionsPage.clickOnFilter();
    }
    @Then("User is presented with the Filter PopUp")
    public void user_is_presented_with_the_filter_pop_up() {
        tendersPage.DisplayFilterPopUp();
    }
    @And("User Query {string} API to fetch data")
    public void user_query_api_to_fetch_data(String seacrhField) {
        searchValues = getAction.searchData(seacrhField);
    }
    @Then("User click on {string} UI to enter data {string} to validate it against API Data")
    public void user_click_on_ui_to_enter_data_to_validate_it_against_api_data(String seacrhField, String searchValue) {
        tendersPage.setSearchValue(seacrhField,searchValue);
        tendersPage.compareUI_API_SearchData(searchValues);
    }

    @When("User input value for {string},{string},{string},{string}, {string}")
    public void user_input_value_for(String string, String string2, String string3, String string4, String string5) throws InterruptedException {
        tendersPage.setSearchField(string,string2,string3,string4,string5);
    }
    @And("Click on Apply Filters")
    public void click_on_apply_filters() {
        uiActionsPage.clickOnApplyFilter();
    }
    @Then("User should be able to verify filter data for {string},{string},{string},{string}, {string}")
    public void user_should_be_able_to_verify_filter_data_for(String string, String string2, String string3, String string4, String string5) {
        List<String> columnNames = tendersPage.getFilterColumns();
        Serenity.reportThat("Validating filter data on UI",
                ()-> assertThat(tendersPage.verifyFilterDataOnUI(columnNames,string,string2,string3,string4,string5)).isTrue());
    }

    @When("User clicks on Clear Filter Button")
    public void user_clicks_on_clear_filter_button() {
        uiActionsPage.clickOnClearFilter();
    }

    @Then("User should see the data accordingly")
    public void user_should_see_the_data_accordingly() {
        tendersPage.DisplayTotalCount();
        if(tendersPage.getTotalCountOnUI().contains(totalRecordCount)){
            Serenity.reportThat("Comparing Total record count on UI against AP",
                    ()-> assertThat(tendersPage.getTotalCountOnUI()).contains(totalRecordCount));
        }

    }


}
