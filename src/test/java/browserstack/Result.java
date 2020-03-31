package browserstack;

public class Result {
    private String status;
    private String reason;

    public Result(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }


}
