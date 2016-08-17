package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 01.08.2016.
 */

public class ContactDeletionTests extends TestBase {

    @BeforeTest
    public void makeSureContactExist() {
        if (app.contact().all().isEmpty()) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData().withFirstname("user2").withLastname("user2").withNickname("user2")
                    .withCompany("mts").withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone("+79111111111").withWorkPhone("+79112222222").withEmail("user1@gmail.com").withGroup("test1"));

        }
        app.goTo().homePage();
    }

    @Test
    public void testContactDeletion() {

        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.contact().all();
        assertThat(after,equalTo(before.without(deletedContact)));


    }




    @Test(enabled = false)
    public void testContactDeletionAll() {
        app.goTo().homePage();
        app.contact().deleteAll();
        Contacts after = app.contact().all();
        Assert.assertTrue(after.isEmpty());
    }



}
