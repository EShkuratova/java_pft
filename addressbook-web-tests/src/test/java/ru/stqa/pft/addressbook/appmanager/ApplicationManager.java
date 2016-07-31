package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class ApplicationManager {
  private FirefoxDriver wd;
  private  ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;



  @BeforeMethod
  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    navigationHelper=new NavigationHelper(wd);
    sessionHelper=new SessionHelper(wd);
    contactHelper=new ContactHelper(wd);
    sessionHelper.login("admin", "secret");

  }

@AfterMethod

  public void stop() {
   wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }


  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
