package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class ContactHelper extends HelperBase {


  private FirefoxDriver wd;

  public ContactHelper(FirefoxDriver wd) {
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

  public void fillContactInfo(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getEmail());

  }

  public void selectContact() {
    //if (!wd.findElement(By.name("selected[]")).isSelected()) {
      click(By.name("selected[]"));
   // }
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
  public void submitContactsDeletion(){
    alertAccept();
  }

  public void initContactModificationByButton() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModificationFromDetails() {
    click(By.name("modifiy"));
  }

  public void getContactDetails() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void checkAllRows() {
    click(By.id("MassCB"));
  }

  public void addNextContact() {
    click(By.linkText("add next"));
  }
}
