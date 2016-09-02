package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @BeforeTest

    public void ensurePreconditions() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2").withHeader("test3"));
        }
    }


    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while(line != null){

            // String[] split = line.split(";");
            //list.add(new Object[]{new GroupData().withName(split[0]).withFooter(split[1])});
            json+=line;
            line = reader.readLine();
        }
        reader.close();
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();

        //return list.iterator();
    }



    @Test(dataProvider ="validContactsFromJson")
    public void testCreateContact(ContactData contact) {

        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().newContactPage();
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");


        app.contact().create(contact.inGroup(groups.iterator().next()));

        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();


    }

    @Test(enabled = false)

    public void testCurrentDir(){
        File currentDir = new File("."); //точка это текущая директория
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


}
