package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");


        app.contact().create(contact.withPhoto(photo));

        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


    }

    @Test

    public void testCurrentDir(){
        File currentDir = new File("."); //точка это текущая директория
        File photo = new File("src/test/resources/StanWithoutHat2.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


}
