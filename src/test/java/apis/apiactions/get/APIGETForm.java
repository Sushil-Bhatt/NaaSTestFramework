package apis.apiactions.get;

public enum APIGETForm {

    OPPORTUNTIES_COUNT_BY_STATUS("/opportunities/count-by-status"),
    CUSTOMER("/opportunities/search-values/field-name=customerName?value="),
    OPPORTUNITY_OWNER("/opportunities/search-values/field-name=opportunityOwner?value="),
    OPPORTUNITY_ID("/opportunities/search-values/field-name=opportunityId?value="),
    TENDER_ID("/opportunities/search-values/field-name=tenderId?value=");

    private String endpoint;

    APIGETForm(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

}
