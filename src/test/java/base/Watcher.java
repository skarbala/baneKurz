package base;

import browserstack.Result;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.BrowserStackIntegration;

public class Watcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        var sessionId = TestBase.getSessionId().toString();
        var result = new Result("passed", "OK");
        BrowserStackIntegration.setResult(sessionId, result);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        var sessionId = TestBase.getSessionId().toString();
        var result = new Result("failed", cause.getMessage());
        BrowserStackIntegration.setResult(sessionId, result);
    }

}
