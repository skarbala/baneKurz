package utils;

import kong.unirest.Unirest;

public class BrowserStackIntegration {

    public static void main(String[] args) {
        Unirest.put("https://api.browserstack.com/automate/sessions/b4275aba42e11ee982e052c7be4882351823e1b4.json")
                .header("Content-Type", "application/json")
                .basicAuth("matko5", "wba3Hey1qW54Lt5Hz67U")
                .body("{\"name\":\"nora\"}")
                .asJson();
    }
}