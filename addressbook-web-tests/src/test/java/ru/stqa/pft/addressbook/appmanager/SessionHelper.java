package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class SessionHelper extends HelperBase{

  public SessionHelper(WebDriver wd) {
    super(wd);
  }
  public void login(String username, String password) {

    type(By.name("user"),username);
    type(By.name("pass"),password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));

  }

}
