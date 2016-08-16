package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);


  @BeforeSuite
  public void setUp() {
    app.init();

  }




  @AfterSuite

  public void tearDown() {
    app.stop();
  }


}
