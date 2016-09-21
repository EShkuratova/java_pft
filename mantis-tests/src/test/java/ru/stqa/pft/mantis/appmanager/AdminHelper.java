package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

/**
 * Created by eshkuratova on 21.09.2016.
 */
public class AdminHelper extends HelperBase{


    private final ApplicationManager app;

    public  AdminHelper(ApplicationManager app){

        super(app.getDriver());
        this.app = app;
 }



    public void resetPassword(UserData user) {

        click(By.linkText(user.getLogin().toString()));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String getConfirmationLink(String email) throws IOException {
        List<MailMessage> mailMessages = null;
        try {
            mailMessages = app.mail().waitForMail(1, 5000);
        } catch (com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException e) {
            e.printStackTrace();
        }
        return findConfirmationLink(mailMessages, email);
    }
    public void resetUserPassword(UserData user) {
        app.session().login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPass"));
        app.goTo().manageUserPage();
        resetPassword(user);
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}
