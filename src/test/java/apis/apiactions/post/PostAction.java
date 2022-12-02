package apis.apiactions.post;

import apis.apiactions.CommonActions;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;

public class PostAction {
    @Step
    public void postRequest_ResponseAsArrayObject(APIPostForm apiForm){
        File file = new File(apiForm.getFilePath());
        SerenityRest.given().contentType("application/json").
                spec(CommonActions.sendReqSpec()).body(file).when().
                post(apiForm.getEndpoint());
    }




}
