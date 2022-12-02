package apis.apiactions.post;

public enum APIPostForm {

    OPPORTUNTIES("/opportunities","src\\main\\resources\\test.json");

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
