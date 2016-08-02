package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class ApplicationManager {
  private WebDriver wd;
  private  ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private String browser;
  private static final String CHROME_PATH="C:\\Users\\EShkuratova\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";

  public ApplicationManager(String browser) {
    this.browser = browser;
  }


  @BeforeMethod
  public void init() {
    if(Objects.equals(browser, BrowserType.FIREFOX)){wd = new FirefoxDriver();}
    else if(Objects.equals(browser, BrowserType.CHROME)){
    ChromeOptions options =new ChromeOptions();
    options.setBinary(CHROME_PATH);
      wd=new ChromeDriver(options);}
    else if(Objects.equals(browser, BrowserType.IE)){wd=new InternetExplorerDriver();}

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
