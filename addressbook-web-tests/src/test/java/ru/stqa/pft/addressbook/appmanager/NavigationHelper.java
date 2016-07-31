package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class NavigationHelper extends HelperBase {


  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));

  }
  public void goToNewContactPage() {
    click(By.linkText("add new"));
  }
}
