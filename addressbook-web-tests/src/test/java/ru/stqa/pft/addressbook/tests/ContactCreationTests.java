package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test
    public void testCreateContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");
        ContactData contact1 = new ContactData().withFirstname("ttttt").withLastname("user2").withNickname("user2")
                .withCompany("mts").withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone( "+79111111111")
                .withWorkPhone("+79112222222").withEmail("user1@gmail.com").withPhoto(photo);
        app.contact().create(contact1);
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact1.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test

    public void testCurrentDir(){
        File currentDir = new File("."); //точка это текущая директория
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


}
