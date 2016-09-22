package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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
       // app.ftp().restore("config.back","config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), BigInteger.valueOf(issueId));
        String status = issueData.getStatus().getName();
        return !status.equals("resolved");
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            System.out.println("Bug is not fixed");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }



}
