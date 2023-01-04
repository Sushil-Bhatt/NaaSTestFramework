package apis.apiactions.post;

public enum APIPostForm {

    NOTIFICATION_MOCK_DATA("/opportunities/save","src\\main\\resources\\notificationData.json"),
    TENDERS_API_DATA("/opportunities","src\\main\\resources\\tendersPageData.json");

    private String endpoint;
    private String filePath;

    APIPostForm(String endpoint, String filePath) {
        this.endpoint = endpoint;
        this.filePath = filePath;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getFilePath() {
        return filePath;
    }

}
