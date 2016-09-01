package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 01.08.2016.
 */
public class ContactModificationTests extends TestBase {
  @BeforeTest
    public void makeSureContactExist() {

      if(app.db().contacts().isEmpty())
        {
            app.goTo().newContactPage();
            app.contact().create(new ContactData().withFirstname("user2").withLastname("user2").withNickname("user2")
                    .withCompany("mts").withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone("+79111111111")
                    .withEmail( "user1@gmail.com"));
            app.goTo().homePage();
        }
    }

    @Test

    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact =before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("user2").withLastname("user2")
                .withNickname("user2").withCompany("mts").withAddress("Санкт-Петербург, Учебный переулок").withEmail("update@gmail.com")
                .withHomePhone(modifiedContact.getHomePhone()).withMobilePhone(modifiedContact.getMobilePhone())
                .withWorkPhone(modifiedContact.getWorkPhone())
                .withEmail2(modifiedContact.getEmail2()).withEmail3(modifiedContact.getEmail3())
                .withPhoto(new File("src/test/resources/StanWithoutHat2.jpg"));
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
        //before = app.contact().all();
        /*app.contact().details(contact);
        app.contact().modifyFromDetails(contact);
        after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before));
*/
    }




}
