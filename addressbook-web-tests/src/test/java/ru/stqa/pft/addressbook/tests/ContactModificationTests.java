package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by eshkuratova on 01.08.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    makeSureContactExist();
    app.getContactHelper().initContactModificationByButton();
    app.getContactHelper().fillContactInfo(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "update@gmail.com","1"),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().getContactDetails();
    app.getContactHelper().initContactModificationFromDetails();
    app.getContactHelper().fillContactInfo(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "update1@gmail.com", null),false);
    app.getContactHelper().submitContactModification();
  }
  public void makeSureContactExist() {
    if(!app.getContactHelper().isAnyContactExist()){
      app.getNavigationHelper().goToNewContactPage();
      app.getContactHelper().createContact(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com","test1"));
      app.getNavigationHelper().goToHomePage();
    }
  }

}
