package browserstack;

import browserstack.BSConfigHelper;
import browserstack.Result;
import kong.unirest.Unirest;

import static browserstack.BSConfigHelper.getConfigValue;

public class BrowserStackIntegration {

    public static void setResult(String sessionId, Result result) {
        Unirest.put(getConfigValue("session-endpoint").concat("{sessionId}.json"))
                .routeParam("sessionId", sessionId)
                .header("Content-Type", "application/json")
                .basicAuth(getConfigValue("name"), getConfigValue("automate-key"))
                .body(result)
                .asJson();
    }

    public static String getPublicUrl(String sessionId) {
        return Unirest.get(getConfigValue("session-endpoint").concat("{sessionId}.json"))
                .routeParam("sessionId", sessionId)
                .basicAuth(getConfigValue("name"), getConfigValue("automate-key"))
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