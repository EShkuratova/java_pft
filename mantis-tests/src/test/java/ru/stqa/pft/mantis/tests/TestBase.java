package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class TestBase {



    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config.back");

    }


    @AfterSuite(alwaysRun = true)

    public void tearDown() throws IOException {
        app.ftp().restore("config.back","config_inc.php");
        app.stop();
    }



}
