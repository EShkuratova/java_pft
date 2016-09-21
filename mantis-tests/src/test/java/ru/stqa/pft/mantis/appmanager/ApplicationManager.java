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
    private RegistrationHelper registratinHelper;
    private FTPHelper ftp;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private NavigationHelper navigationHelper;
    private AdminHelper adminHelper;
    private SessionHelper sessionHelper;
    private UserHelper userHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }


    @BeforeMethod
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


         // sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));

    }

    @AfterMethod

    public void stop() {
        if(wd != null){
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key){
      return  properties.getProperty(key);
    }


    public RegistrationHelper registration() {
        if(registratinHelper == null){
            registratinHelper =  new RegistrationHelper(this);
        }
        return registratinHelper;
    }

    public FTPHelper ftp(){
        if(ftp == null){
            ftp = new FTPHelper(this);
        }
        return ftp;
    }
    public UserHelper user(){
        if(userHelper == null){
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }
    
    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DbHelper db() {
        dbHelper = new DbHelper();
        return  dbHelper;
    }

    public NavigationHelper goTo() {
        navigationHelper = new NavigationHelper(this);
        return navigationHelper;
    }

    public AdminHelper admin() {
        adminHelper = new AdminHelper(this);
        return adminHelper;
    }
    public SessionHelper session() {
        sessionHelper = new SessionHelper(this.getDriver());
        return sessionHelper;
    }

    public WebDriver getDriver() {
        if(wd == null){
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

        }
        return wd;
    }
    public void timeout(int seconds) {
        wd.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
