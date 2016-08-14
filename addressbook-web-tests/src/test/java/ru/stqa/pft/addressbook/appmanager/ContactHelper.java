package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
    if(!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[3]"))) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
    }

    if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[2]"))) {
       click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
      }
  }

  public void fillContactInfo(ContactData contactData,boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getEmail());
    if(creation) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
    else Assert.assertFalse(isElementPresent(By.name("new_group")),"На форме редактирования доступен элемент для выбора группы!");

  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
  public void submitContactsDeletion(){
    alertAccept();
  }

  public void initContactModificationByButton(int i) {
    click(By.xpath("//table[@id='maintable']/tbody/tr["+i+"]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModificationFromDetails() {
    click(By.name("modifiy"));
  }

  public void getContactDetails(int i) {
    click(By.xpath("//table[@id='maintable']/tbody/tr["+i+"]/td[7]/a/img"));
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

  public void createContact(ContactData contactData){

  fillContactInfo(contactData,true);
  fillBirthday();
  submitContactCreation();
  }

  public boolean isAnyContactExist() {

    return isElementPresent(By.name("selected[]"));
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for(WebElement elem: elements){
      int id = Integer.parseInt(elem.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = elem.findElement(By.xpath("./td[3]")).getText();
      String lastname = elem.findElement(By.xpath("./td[2]")).getText();
      contacts.add(new ContactData(id,firstname,lastname));

    }
    return contacts;

  }
}
