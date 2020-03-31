package utils;

import kong.unirest.Unirest;

public class BrowserStackIntegration {

    public static void main(String[] args) {
        setResult(
                "b4275aba42e11ee982e052c7be4882351823e1b4",
                "passed",
                "som opat stastna ze brano je triezvy"
        );
    }

    public static void setResult(String sessionId, String status, String reason) {
        Unirest.put("https://api.browserstack.com/automate/sessions/{sessionId}.json")
                .routeParam("sessionId", sessionId)
                .header("Content-Type", "application/json")
                .basicAuth("matko5", "wba3Hey1qW54Lt5Hz67U")
                .body("{\"status\":\"" + status + "\", \"reason\":\"" + reason + "\"}")
                .asJson();
    }
}