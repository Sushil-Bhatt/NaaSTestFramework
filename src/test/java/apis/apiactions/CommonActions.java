package apis.apiactions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonActions {
    public static RequestSpecification sendReqSpec() {
        EnvironmentVariables environmentVariables = Injectors.getInjector()
                .getInstance(EnvironmentVariables.class);

        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("baseurl");

        System.out.println(baseUrl);
        return new RequestSpecBuilder().setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }

    public List<String> ObjectStateKeys(List<Map<String,String>> tenderStatusObjectList) {
        List<String> objectState = new ArrayList<>();
        String keyString = null;
        for (Map<String, String> tenderStatusobjectMap : tenderStatusObjectList) {
            Set<String> keys = tenderStatusobjectMap.keySet();
            for (String k : keys) {
                objectState.add(k);
            }

        }
        return objectState;
    }

    public List<String> ObjectStateValue(List<Map<String,String>> tenderStatusObjectList, List<String> opportunity) {
        List<String> objectStateValue = new ArrayList<>();
        for (Map<String, String> tenderStatusobjectMap : tenderStatusObjectList) {
            for(int i=0;i<opportunity.size();i++){
                if(tenderStatusobjectMap.containsKey(opportunity.get(i)))
                    objectStateValue.add(tenderStatusobjectMap.get(opportunity.get(i)));
            }
        }
        return objectStateValue;
    }

}
