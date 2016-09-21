package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by eshkuratova on 21.09.2016.
 */
public class NavigationHelper extends HelperBase {
    public NavigationHelper(ApplicationManager app) {
        super(app.getDriver());
    }

    public void manageUserPage() {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
    }

}
