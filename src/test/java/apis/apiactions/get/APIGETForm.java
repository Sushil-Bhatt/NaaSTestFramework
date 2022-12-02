package apis.apiactions.get;

public enum APIGETForm {

    OPPORTUNTIES_COUNT_BY_STATUS("/opportunities/count-by-status");

    private String endpoint;

    APIGETForm(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

}
