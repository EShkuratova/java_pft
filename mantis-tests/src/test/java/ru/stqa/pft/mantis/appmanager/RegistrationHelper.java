package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by eshkuratova on 20.09.2016.
 */
public class RegistrationHelper extends HelperBase{
    private final ApplicationManager app;
    //private WebDriver wd;

    public RegistrationHelper(ApplicationManager manager) {
        super(manager.getDriver());
        this.app = manager;
       // this.wd = app.getDriver();

    }

    public void start(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),user);
        type(By.name("email"),email);
        click(By.cssSelector("input[value = 'Signup']"));

    }

    public void finish(String link, String pass) {
        wd.get(link);
        type(By.name("password"), pass);
        type(By.name("password_confirm"), pass);
        click(By.cssSelector("input[value = 'Update User']"));
    }
}
