package stepDef;

import apis.apiactions.CommonActions;
import apis.apiactions.get.GetAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.collaborationportal.TenderStatusPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static apis.apiactions.get.APIGETForm.OPPORTUNTIES_COUNT_BY_STATUS;
import static org.assertj.core.api.Assertions.assertThat;

public class TenderStatus {
    TenderStatusPage tenderStatus;

    @Steps
    GetAction getAction;

    @Steps
    CommonActions commonActions;

    private static List<String> opportunities = new ArrayList<>();
    private static List<String> opportunitiesValue = new ArrayList<>();

    @Given("User is on NaaS Dashboard")
    public void user_is_on_naa_s_dashboard() {
        tenderStatus.open();
    }

    @And("User is presented with Tender Status component on the dashboard")
    public void user_is_presented_with_tender_status_component_on_the_dashboard() {
        tenderStatus.displayTenderStatusTable();
    }

    @When("User Query the Tender Status API for {string} & {string}")
    public void user_query_the_tender_status_api(String s1, String s2) {
        getAction.getRequest_Response(OPPORTUNTIES_COUNT_BY_STATUS);
        List<Map<String,String>> tenderStatusObject = getAction.readResponseData_TenderStatus(s1,s2);
        opportunities = commonActions.ObjectStateKeys(tenderStatusObject);
        opportunitiesValue = commonActions.ObjectStateValue(tenderStatusObject,opportunities);
    }

    @Then("User should be able to validate the API data with UI data for opportunityStatus")
    public void user_should_be_able_to_validate_the_api_data_with_ui_data_for_opportunity_status() {
        for(int i=0;i<opportunities.size();i++) {
            int finalI = i;
            Serenity.reportThat("Tender Table Opportunity & its count should match with API data",
                    ()-> assertThat(tenderStatus.tenderTableObject(opportunities.get(finalI))).isEqualTo(opportunitiesValue.get(finalI)));
            Serenity.recordReportData().withTitle("Opportunity status "+opportunities.get(finalI)).andContents("count is "+opportunitiesValue.get(finalI));
        }
    }


}
