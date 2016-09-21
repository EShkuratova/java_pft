package ru.stqa.pft.mantis.appmanager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

/**
 * Created by eshkuratova on 21.09.2016.
 */
public class UserHelper extends HelperBase {
    private final ApplicationManager app;

    public UserHelper(ApplicationManager app){
        super(app.getDriver());
        this.app = app;
    }
    public String registry(String email, String user, String pass) {
        app.registration().start(user, email);
        List<MailMessage> mailMessages = null;
        try {
            mailMessages = app.mail().waitForMail(2, 10000);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String link = findConfirmationLink(mailMessages, email);
        app.registration().finish(link, pass);
        return pass;
    }

    private String findConfirmationLink(List<MailMessage> messages, String email) {

        MailMessage message = messages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);



    }

}
