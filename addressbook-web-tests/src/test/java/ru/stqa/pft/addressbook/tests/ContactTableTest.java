package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 18.08.2016.
 */
public class ContactTableTest extends TestBase {

    @BeforeTest
    public void makeSureContactExist() {
        if (app.db().contacts().isEmpty()) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData().withFirstname("user2").withLastname("user2")
                    .withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone("+79111111111")
                    .withWorkPhone("+79112222222").withHomePhone("+7911").withPhoto(new File("src/test/resources/StanWithoutHat2.jpg"))
                    .withEmail("user1@gmail.com").withEmail2("user1@yandex.ru"));

        }
        app.goTo().homePage();
    }

    @Test

    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData dataFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contact.getAllPhones()), equalTo(mergePhones(dataFromEditForm)));


    }

    @Test

    public void testContactEmails(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData dataFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat((contact.getAllEmails().replaceAll("\\s","")), equalTo(mergeEmails(dataFromEditForm)));

    }

    private String mergeEmails(ContactData form) {
       return Arrays.asList(form.getEmail(),form.getEmail2(),form.getEmail3()).stream()
                .filter(s -> !(s == null || s.equals(""))).map(s -> s.replaceAll("\\s",""))
                .collect(Collectors.joining());
    }

    @Test

    public void testContactAddress(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData dataFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(dataFromEditForm.getAddress())));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s -> !s.equals("")))
                .map(ContactTableTest::cleaned)
                .collect(Collectors.joining());

    }

    public static String  cleaned(String s){
        return s.replaceAll("\\s","").replaceAll("[-()]","");
    }


}
