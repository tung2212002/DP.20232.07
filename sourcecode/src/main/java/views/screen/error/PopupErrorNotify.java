package views.screen.error;

import views.screen.popup.PopupScreen;

import java.io.IOException;

public class PopupErrorNotify extends ErrorNotify {
    private static PopupErrorNotify instance;

    private PopupErrorNotify() {
    }

    public static PopupErrorNotify instance() {
        if (instance == null) {
            instance = new PopupErrorNotify();
        }
        return instance;
    }

    @Override
    public void notify(String message) throws IOException {
        PopupScreen.error(message);
    }

    public static ErrorNotify getInstance() {
        return instance;
    }
}
