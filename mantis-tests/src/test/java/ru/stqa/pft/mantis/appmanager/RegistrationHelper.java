package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by eshkuratova on 20.09.2016.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager manager) {
        this.app = manager;
        this.wd = app.getDriver();
    }

    public void start(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");

    }
}
