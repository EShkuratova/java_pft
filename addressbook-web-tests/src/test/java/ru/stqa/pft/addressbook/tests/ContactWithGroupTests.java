package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 01.09.2016.
 */
public class ContactWithGroupTests extends TestBase {

@BeforeMethod
public void preCondition(){

    List<Groups> listGroups= new ArrayList<Groups>();
    for(ContactData cd:app.db().contacts())
    {
            listGroups.add(cd.getGroups());
    }
    if(listGroups.size() == 0) {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            GroupData gr = new GroupData().withName(this.getClass().getTypeName())
                    .withHeader(this.getClass().getTypeName())
                    .withFooter(this.getClass().getTypeName());
                    app.group().create(gr);
        }
        app.goTo().newContactPage();
        app.contact().create(new ContactData().withFirstname(this.getClass().getSimpleName())
                .withLastname(this.getClass().getSimpleName()).inGroup(app.db().groups().iterator().next()));
    }
    //Хотим иметь новую группу без пользователей
    app.goTo().groupPage();
    GroupData group = new GroupData().withName(this.getClass().getSimpleName())
            .withHeader(this.getClass().getSimpleName())
            .withFooter(this.getClass().getSimpleName());
    app.group().create(group);
}

    @Test
   public void addingToGroupTest() {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        Groups after = app.db().groups();
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsBefore = contact.getGroups();
        app.contact().addToGroup(contact, group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()));
        Groups groupsAfter = new Groups();
        for (ContactData cd : app.db().contacts()) {
            if (cd.getId() == contact.getId()) {
                groupsAfter = cd.getGroups();
                break;
            }
        }

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
 @Test
    public void deleteFromGroupTest(){
    Contacts contacts = app.db().contacts();
    ContactData contact = new ContactData();
        for (ContactData cd : contacts) {
            if (!cd.getGroups().isEmpty()){
                contact = cd;
                break;
            }
        }
    GroupData group = contact.getGroups().iterator().next();
     app.goTo().homePage();
     app.contact().deleteFromGroup(contact,group);
     Groups groupsAfter = new Groups();
     for (ContactData cd : app.db().contacts()) {
         if (cd.getId() == contact.getId()) {
             groupsAfter = cd.getGroups();
             break;
         }
     }
     assertThat(groupsAfter,equalTo(contact.getGroups().without(group)));
        }

}



