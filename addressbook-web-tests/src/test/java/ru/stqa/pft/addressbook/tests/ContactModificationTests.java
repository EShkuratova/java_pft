package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by eshkuratova on 01.08.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test(enabled = false)

  public void testContactModification(){
    app.goTo().goToHomePage();
    makeSureContactExist();
    ContactData contact = new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "update@gmail.com","1");
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationByButton(before.size()+1);
    app.getContactHelper().fillContactInfo(contact,false);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
    after.sort(byId);
    before.sort(byId);
    Assert.assertEquals(after,before);
    before = app.getContactHelper().getContactList();
    app.getContactHelper().getContactDetails(before.size()+1);
    app.getContactHelper().initContactModificationFromDetails();
    app.getContactHelper().fillContactInfo(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "update1@gmail.com", null),false);
    app.getContactHelper().submitContactModification();
    after = app.getContactHelper().getContactList();
    after.sort(byId);
    before.sort(byId);
    Assert.assertEquals(after,before);

 }
  public void makeSureContactExist() {
    if(!app.getContactHelper().isAnyContactExist()){
      app.goTo().goToNewContactPage();
      app.getContactHelper().createContact(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1"));
      app.goTo().goToHomePage();
    }
  }

}
