package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by eshkuratova on 01.08.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){

   // app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().goToHomePage();
  }

@Test
  public void testContactDeletionAll(){

    app.getContactHelper().checkAllRows();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
  }
}
