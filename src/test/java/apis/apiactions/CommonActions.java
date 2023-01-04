package apis.apiactions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
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

        return new RequestSpecBuilder().setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification buildReqSpec() {
        EnvironmentVariables environmentVariables = Injectors.getInjector()
                .getInstance(EnvironmentVariables.class);

        String baseUrlMockData = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("baseurlOpportunity");

        return new RequestSpecBuilder().setBaseUri(baseUrlMockData)
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification buildAuthorityReqSpec() {
        EnvironmentVariables environmentVariables = Injectors.getInjector()
                .getInstance(EnvironmentVariables.class);

        String baseUrlMockData = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("baseurlAuthorityMatrix");

        return new RequestSpecBuilder().setBaseUri(baseUrlMockData)
                .setContentType("application/json")
                .build();
    }

    public List<String> ObjectStateKeys(List<Map<String,String>> tenderStatusObjectList) {
        List<String> objectState = new ArrayList<>();
        for (Map<String, String> tenderStatusobjectMap : tenderStatusObjectList) {
            Set<String> keys = tenderStatusobjectMap.keySet();
            objectState.addAll(keys);
        }
        return objectState;
    }

    public List<String> ObjectStateValue(List<Map<String,String>> tenderStatusObjectList, List<String> opportunity) {
        List<String> objectStateValue = new ArrayList<>();
        for (Map<String, String> tenderStatusobjectMap : tenderStatusObjectList) {
            for (String s : opportunity) {
                if (tenderStatusobjectMap.containsKey(s))
                    objectStateValue.add(tenderStatusobjectMap.get(s));
            }
        }
        return objectStateValue;
    }

}
