package utils;

import browserstack.Result;
import kong.unirest.Unirest;

public class BrowserStackIntegration {

    public static void setResult(String sessionId, Result result) {
        Unirest.put("https://api.browserstack.com/automate/sessions/{sessionId}.json")
                .routeParam("sessionId", sessionId)
                .header("Content-Type", "application/json")
                .basicAuth("matko5", "wba3Hey1qW54Lt5Hz67U")
                .body(result)
                .asJson();
    }
}