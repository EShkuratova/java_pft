package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by eshkuratova on 21.09.2016.
 */
public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void Precondition() throws RemoteException, ServiceException, MalformedURLException {
        app.mail().start();
        skipIfNotFixed(0000001);
        if (app.db().users().size() <= 1) {
            app.mail().start();
            long now = System.currentTimeMillis();
            String email = "email" + now + "@localhost.localdomain";
            String user = "user" + now;
            String pass = "pass";
            app.user().registry(email, user, pass);
            app.mail().stop();

        }

    }


    @Test
    public void testPasswordChange() throws IOException, MessagingException {

        UserData user = app.db().users().iterator().next();
        if (user.getLogin() == "administrator") {
            user = app.db().users().iterator().next();
        }

        String email = user.getEmail();
        String newPassword = "newPassword";

        app.admin().resetUserPassword(user);

        String confirmationLink = app.admin().getConfirmationLink(email);
        app.timeout(5);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user.getLogin(), newPassword));
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}



