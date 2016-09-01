package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillBirthday() {
        if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[3]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
        }

        if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[2]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
        }
    }

    public void fillContactInfo(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"),contactData.getPhoto());
       if (creation) {
           if(contactData.getGroups().size() > 0){
               Assert.assertTrue(contactData.getGroups().size()==1);
           new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
       }
       }
        else
            Assert.assertFalse(isElementPresent(By.name("new_group")), "На форме редактирования доступен элемент для выбора группы!");

    }

    public int count() {
        int count = wd.findElements(By.name("selected[]")).size();
        return count;
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactsDeletion() {
        alertAccept();
    }

    public void initContactModificationByButton(int i) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[8]/a/img"));
    }

    public void initContactModificationByButton(ContactData contact) {
       // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",contact.getId()))).click();
       wd.findElement(By.xpath("//input[@value='" + contact.getId() + "']//..//..//a[contains(@href,'edit')]")).click();

    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactModificationFromDetails() {
        click(By.name("modifiy"));
    }


    public void getContactDetails(int i) {

        click(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[7]/a/img"));
    }

    public void details(ContactData contact) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']",contact.getId()))).click();
        //wd.findElement(By.xpath("//input[@value='" + contact.getId() + "']//..//..//a[contains(@href,'details')]")).click();
    }

    public void checkAllRows() {
        click(By.id("MassCB"));
    }

    public void addNextContact() {
        click(By.linkText("add next"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }

    public void selectContact(ContactData contact) {
        //wd.findElements(By.name("selected[]")).get(index).click();
        wd.findElement(By.cssSelector("input[value ='" + contact.getId() + "' ]")).click();
        //click(By.name("selected[]"));
    }

    public void create(ContactData contactData) {

        fillContactInfo(contactData, true);
        //fillBirthday();
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationByButton(contact);
        fillContactInfo(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData deletedContact) {
        selectContact(deletedContact);
        deleteSelectedContacts();
        submitContactsDeletion();
        contactCache = null;

    }

    public void deleteAll() {
        checkAllRows();
        deleteSelectedContacts();
        submitContactsDeletion();
        contactCache = null;
    }

     public ContactData infoFromEditForm(ContactData contact){

         initContactModificationByButton(contact);
         String workPhone = wd.findElement(By.name("work")).getAttribute("value");
         String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
         String homePhone = wd.findElement(By.name("home")).getAttribute("value");
         String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
         String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
         String email = wd.findElement(By.name("email")).getAttribute("value");
         String email2 = wd.findElement(By.name("email2")).getAttribute("value");
         String email3 = wd.findElement(By.name("email3")).getAttribute("value");
         String address = wd.findElement(By.name("address")).getText();

         return
                 new ContactData().withMobilePhone(mobilePhone).withWorkPhone(workPhone).withFirstname(firstname)
                         .withLastname(lastname).withHomePhone(homePhone).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

     }

    public ContactData infoFromDetailForm(ContactData contact){

        details(contact);
        String fullInfo = wd.findElement(By.cssSelector("#content")).getText();
        System.out.println(fullInfo);
        return
                new ContactData().withFullInfo(fullInfo);

    }
    public boolean isAnyContactExist() {

        return isElementPresent(By.name("selected[]"));
    }
  private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement elem : elements) {
            int id = Integer.parseInt(elem.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = elem.findElement(By.xpath("./td[3]")).getText();
            String lastname = elem.findElement(By.xpath("./td[2]")).getText();
            String allPhones = elem.findElement(By.xpath("./td[6]")).getText();
            String allEmails = elem.findElement(By.xpath("./td[5]")).getText();
            String address = elem.findElement(By.xpath("./td[4]")).getText();
            //String [] phones = allPhones.split("\n");

            //contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withMobilePhone(phones[0]).withWorkPhone(phones[1]));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }

        return new Contacts(contactCache);

    }


    public void modifyFromDetails(ContactData contact) {
        initContactModificationFromDetails();
        fillContactInfo(contact, false);
        submitContactModification();
    }
}
