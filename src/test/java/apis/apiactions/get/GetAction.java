package apis.apiactions.get;

import apis.apiactions.CommonActions;
import apis.apiactions.post.APIPostForm;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.util.*;

import static apis.apiactions.get.APIGETForm.*;
import static apis.apiactions.get.APIGETForm.CUSTOMER;

public class GetAction {

    @Step
    public void getRequest_Response(APIGETForm apigetForm){
        SerenityRest.given().contentType("application/json").
                spec(CommonActions.sendReqSpec()).when().
                get(apigetForm.getEndpoint());
    }

    @Step
    public void getRequest_Search(APIGETForm apiSearchForm){
        SerenityRest.given().contentType("application/json").header("X-TenantID","ocean").
                spec(CommonActions.buildReqSpec()).when().
                get(apiSearchForm.getEndpoint());
    }

    public List<Map<String,String>> readResponseData_TenderStatus(String param1, String param2,String param3){
        List<Map<String,String>> dataListMap = new ArrayList<>();
        List<Map<String,Object>> all_data = SerenityRest.lastResponse().getBody().
                jsonPath().get(param3);

        for(Map<String,Object> objectMap: all_data){
            if (objectMap.get(param1)!=null){
                LinkedHashMap<String,String> dataMap = new LinkedHashMap<>();
                dataMap.put(objectMap.get(param1).toString(),objectMap.get(param2).toString());
                dataListMap.add(dataMap);
            }
        }
        return dataListMap;
    }

    public List<String> searchData(String searchField){
        if(Objects.equals(searchField, "tenderid")){
            getRequest_Search(TENDER_ID);
        } else if (Objects.equals(searchField, "opportunityid")) {
            getRequest_Search(OPPORTUNITY_ID);
        } else if (Objects.equals(searchField, "opportunityowner")) {
            getRequest_Search(OPPORTUNITY_OWNER);
        } else if (Objects.equals(searchField, "customer")) {
            getRequest_Search(CUSTOMER);
        }

        return SerenityRest.lastResponse().getBody().jsonPath().get("values");
    }

}
