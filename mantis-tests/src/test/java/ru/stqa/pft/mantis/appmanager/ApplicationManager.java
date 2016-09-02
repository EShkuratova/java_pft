package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private static final String CHROME_PATH = "C:\\Users\\EShkuratova\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";




    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }


    @BeforeMethod
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary(CHROME_PATH);
            wd = new ChromeDriver(options);
        } else if (Objects.equals(browser, BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        wd.get(properties.getProperty("web.baseUrl"));
         // sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));

    }

    @AfterMethod

    public void stop() {
        wd.quit();
    }


}
