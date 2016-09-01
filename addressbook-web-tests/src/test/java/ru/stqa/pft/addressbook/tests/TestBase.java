package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
       Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();

    }


    @AfterSuite

    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start " + m.getName() + " с параметрами " + Arrays.asList(p));

    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop " +m.getName());
    }

    public void verifyGroupListInUI() {
        System.out.println(Boolean.getBoolean("verifyUI"));
        if(Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            logger.info("<<<<<<<<<<<<<<<<<<<Выполняем проверку UI");
            assertThat(uiGroups, equalTo(dbGroups.stream().map(g -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUI() {
        System.out.println(Boolean.getBoolean("verifyUI"));
        if(Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            logger.info("<<<<<<<<<<<<<<<<<<<Выполняем проверку UI");
            System.out.println("From UI^ " +uiContacts);
            System.out.println("From DB^ " +dbContacts);

            assertThat(uiContacts, equalTo(dbContacts.stream().map(g -> {
                return new ContactData().withId(g.getId())
                        .withFirstname(g.getFirstname()).withLastname(g.getLastname()).withAllPhones(mergePhones(g))
                        .withAllEmails(mergeEmails(g));
            })
                    .collect(Collectors.toSet())));
        }
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s -> !s.equals("")))
                .map(ContactTableTest::cleaned)
                .collect(Collectors.joining());

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s -> !s.equals("")))
                .map(ContactTableTest::cleaned)
                .collect(Collectors.joining());
    }


}
