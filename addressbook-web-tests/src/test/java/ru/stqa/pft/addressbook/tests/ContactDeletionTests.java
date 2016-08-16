package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by eshkuratova on 01.08.2016.
 */

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {

    app.goTo().goToHomePage();
    makeSureContactExist();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size()-1);
    Assert.assertEquals(after.size(),before.size());
    Assert.assertEquals(after,before);

  }



  @Test(enabled = false)
  public void testContactDeletionAll() {
    app.goTo().goToHomePage();
    makeSureContactExist();
    app.getContactHelper().checkAllRows();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertTrue(after.isEmpty());
  }

  public void makeSureContactExist() {
    if(!app.getContactHelper().isAnyContactExist()){
      app.goTo().goToNewContactPage();
      app.getContactHelper().createContact(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1"));
      app.goTo().goToHomePage();
    }
  }
}
