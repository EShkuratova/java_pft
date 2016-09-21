package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}


