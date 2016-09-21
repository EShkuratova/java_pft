package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by eshkuratova on 20.09.2016.
 */
public class RegistrationTests  extends TestBase{
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @AfterMethod(alwaysRun = true)
    public  void stopMailServer(){
        app.mail().stop();
    }


    @Test
    public void testRegistration(){
        long now = System.currentTimeMillis();
        String email = "email3"+ now + "@localhost.localdomain";
        String user = "user3" +now;
        String pass = "pass";
        app.user().registry(email, user, pass);

        try {
            assertTrue(app.newSession().login(user, pass));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
