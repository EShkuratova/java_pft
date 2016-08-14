package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testCreateContact() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToNewContactPage();
    ContactData contact1 = new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1");
    ContactData contact2 = new ContactData("user3", "user3", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1");
    app.getContactHelper().fillContactInfo(contact1,true);
    app.getContactHelper().fillBirthday();
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().addNextContact();
    app.getContactHelper().fillContactInfo(contact2,true);
    app.getContactHelper().fillBirthday();
    app.getContactHelper().submitContactCreation();
    List<ContactData> after = app.getContactHelper().getContactList();

    Comparator<? super ContactData> byId = (o1,o2) -> Integer.compare(o1.getId(),o2.getId());
    contact1.setId(after.stream().max(byId).get().getId()-1);
    contact2.setId(after.stream().max(byId).get().getId());
    before.add(contact1);
    before.add(contact2);
    Assert.assertEquals(after.size(),before.size());
    after.sort(byId);
    before.sort(byId);
    Assert.assertEquals(after,before);

  }


}
