package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 24.08.2016.
 */
public class ContactDetailsTest extends TestBase {


    @BeforeTest
    public void makeSureContactExist() {
        if (app.contact().all().isEmpty()) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData().withFirstname("user2").withLastname("user2")
                    .withAddress("Санкт-Петербург, Учебный переулок").withMobilePhone("+79111111111")
                    .withWorkPhone("+79112222222").withHomePhone("+7911")
                    .withEmail("user1@gmail.com").withEmail2("user1@yandex.ru"));

        }
        app.goTo().homePage();
    }

    @Test

    public void testContactName(){

        ContactData contactFromTable = app.contact().all().iterator().next();
        ContactData contactFromDetail = app.contact().infoFromDetailForm(contactFromTable);
        app.goTo().homePage();
        ContactData contactFromEdit = app.contact().infoFromEditForm(contactFromTable);
        System.out.println(contactFromDetail);
        System.out.println(contactFromEdit);
        assertThat(mergeAllInfo(contactFromEdit).replaceAll("\\s","").replaceAll("-()",""),
                equalTo(contactFromDetail.getFullInfo().replaceAll("\\s","").replaceAll("-()","")));
    }

    private String mergeAllInfo(ContactData contact) {

        String fullName = Arrays.asList(contact.getFirstname(),contact.getLastname()).stream().filter(s -> !(s == null || s == ""))
                .map(s -> s.replaceAll("\\s","")).collect(Collectors.joining());
        String address = contact.getAddress();
        String homePhone = "";
        String mobilePhone = "";
        String workPhone = "";
        if(!contact.getHomePhone().isEmpty())
        {
           homePhone = cleanPhone("H: " + contact.getHomePhone());
        }
        if(!contact.getMobilePhone().isEmpty())
        {
            mobilePhone = cleanPhone("M: " + contact.getMobilePhone());
        }
        if(!contact.getWorkPhone().isEmpty())
        {
            workPhone = cleanPhone("W: " + contact.getWorkPhone());
        }
        String email = "";
        String email2 = "";
        String email3 = "";

        if(!contact.getEmail().isEmpty())
        {
            email = contact.getEmail() + "(www." + contact.getEmail().substring(contact.getEmail().indexOf("@") + 1) + ")";
            System.out.println(email);
        }
        if(!contact.getEmail2().isEmpty())
        {
            email2 = contact.getEmail2() + "(www." + contact.getEmail2().substring(contact.getEmail2().indexOf("@") + 1) + ")";

        }
        if(!contact.getEmail3().isEmpty())
        {
            email3 = contact.getEmail3() + "(www." + contact.getEmail3().substring(contact.getEmail3().indexOf("@") + 1) + ")";
        }
        return fullName + address + homePhone + mobilePhone + workPhone +email + email2 + email3;
    }



  private  String cleanPhone(String s) {
      return s.replaceAll("\\s", "").replaceAll("-()", "");
  }
}
