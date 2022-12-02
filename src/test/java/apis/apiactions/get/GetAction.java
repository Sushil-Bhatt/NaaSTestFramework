package apis.apiactions.get;

import apis.apiactions.CommonActions;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAction {

    @Step
    public void getRequest_Response(APIGETForm apigetForm){
        SerenityRest.given().contentType("application/json").
                spec(CommonActions.sendReqSpec()).when().
                get(apigetForm.getEndpoint());
    }

    public List<Map<String,String>> readResponseData_TenderStatus(String param1, String param2){
        List<Map<String,String>> dataListMap = new ArrayList<>();
        List<Map<String,Object>> all_data = SerenityRest.lastResponse().getBody().
                jsonPath().get("data");

        for(Map<String,Object> objectMap: all_data){
            if (objectMap.get(param1)!=null){
                Map<String,String> dataMap = new HashMap<>();
                dataMap.put(objectMap.get(param1).toString(),objectMap.get(param2).toString());
                dataListMap.add(dataMap);
            }
        }
        return dataListMap;
    }

}
