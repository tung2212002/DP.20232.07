package views.screen.error;

import java.io.IOException;

public abstract class ErrorNotify {
    private static ErrorNotify instance;

    protected static ErrorNotify instance() {
        return null;
    }

    public void notify(String message) throws IOException {
    }

    public static ErrorNotify getInstance() {
        return instance;
    }
}
