package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by eshkuratova on 01.08.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().goToHomePage();
    makeSureContactExist();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().goToHomePage();
  }



  @Test
  public void testContactDeletionAll() {
    app.getNavigationHelper().goToHomePage();
    makeSureContactExist();
    app.getContactHelper().checkAllRows();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
  }

  public void makeSureContactExist() {
    if(!app.getContactHelper().isAnyContactExist()){
      app.getNavigationHelper().goToNewContactPage();
      app.getContactHelper().createContact(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1"));
      app.getNavigationHelper().goToHomePage();
    }
  }
}
