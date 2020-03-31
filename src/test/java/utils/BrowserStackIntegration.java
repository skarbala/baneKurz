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

    public static String getPublicUrl(String sessionId) {
        return Unirest.get("https://api.browserstack.com/automate/sessions/{sessionId}.json")
                .routeParam("sessionId", sessionId)
                .basicAuth("matko5", "wba3Hey1qW54Lt5Hz67U")
                .asJson().getBody()
                .getObject()
                .getJSONObject("automation_session")
                .get("public_url")
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(getPublicUrl("8b4ee4972f4e0edd11e32cc2b91919cdd257f394"));

    }
}