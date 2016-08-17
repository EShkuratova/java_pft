package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test
    public void testCreateContact() {
        app.goTo().homePage();
        HashSet<ContactData> before = app.contact().all();
        app.goTo().newContactPage();
        ContactData contact1 = new ContactData().withFirstname("ttttt").withLastname("user2").withNickname("user2")
                .withCompany("mts").withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone( "+79111111111")
                .withWorkPhone("+79112222222").withEmail("user1@gmail.com").withGroup("test1");
        app.contact().create(contact1);
        HashSet<ContactData> after = app.contact().all();
        contact1.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact1);
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before));

    }


}
