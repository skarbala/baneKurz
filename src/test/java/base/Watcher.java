package base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.BrowserStackIntegration;

public class Watcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        var sessionId = TestBase.getSessionId().toString();
        BrowserStackIntegration.setResult(sessionId, "passed", "OK");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        var sessionId = TestBase.getSessionId().toString();
        BrowserStackIntegration.setResult(sessionId, "failed", "NOK");
    }

}
