package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testCreateContact() {

    app.getNavigationHelper().goToNewContactPage();
    app.getContactHelper().fillContactInfo(new ContactData("user2", "user2", "user2", "mts", "Санкт-Петербург, Учебный переулок", "+79111111111", "+79112222222", "user1@gmail.com"));
    app.getContactHelper().fillBirthday();
    app.getContactHelper().submitContactCreation();
  }


}
