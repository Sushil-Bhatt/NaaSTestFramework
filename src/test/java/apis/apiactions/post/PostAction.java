package apis.apiactions.post;

import apis.apiactions.CommonActions;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PostAction {
    @Step
    public void postRequest_ResponseAsArrayObject(APIPostForm apiForm){
        File file = new File(apiForm.getFilePath());
        SerenityRest.given().contentType("application/json").
                spec(CommonActions.sendReqSpec()).body(file).when().
                post(apiForm.getEndpoint());
    }

    @Step
    public void postRequest_Response(APIPostForm apiForm){
        Response response = SerenityRest.given().contentType("application/json").header("X-TenantID","ocean").
                spec(CommonActions.buildReqSpec()).body(new File(apiForm.getFilePath())).when().
                post(apiForm.getEndpoint());
    }

    @Step
    public void postRequest_Response_userManagementScreen(){
        Response response = SerenityRest.given().contentType("application/json").header("X-TenantID","ocean").
                spec(CommonActions.buildAuthorityReqSpec()).body("[]").when().
                post("/userRoleMappings");
    }

    public boolean verifyPostSuccess_MockData(){
        return (SerenityRest.lastResponse().getBody().asString().trim()).contains("Successfully saved opportunity task. Notification Status - Component - Notification: true   Component - PendingAction: true");
    }



}
