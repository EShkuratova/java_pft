package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eshkuratova on 25.08.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c" , description = "Group count")
    int count;

    @Parameter(names = "-f" , description = "Относительный путь к файлу")
    String file;
    @Parameter(names = "-d" , description = "Data format")
    String format = "";


     public static void main(String [] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {
            jcommander.parse(args);
        }catch (ParameterException ex) {
            jcommander.usage();
            return;
        }
        generator.run();



    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        switch (format) {
            case "csv":
                saveAsCsv(contacts, new File(file));
                break;
            case "xml":
                saveAsxml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts,new File(file));
                break;
            default:
                System.out.println("Неизвестный формат файла");
                break;
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
       try(Writer writer = new FileWriter(file)){
           writer.write(json);
       }


    }


    private void saveAsxml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        //xstream.alias("group",GroupData.class);
        String xml = xstream.toXML(contacts);
       try(Writer writer = new FileWriter(file)){
           writer.write(xml);
       }

    }


    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)){
            for(ContactData contact:contacts){
                writer.write(String.format("%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),contact.getEmail()));
            }

        }

    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();

        for(int i=0;i<count;i++){

            contacts.add(new ContactData().withFirstname(String.format("test %s",i)).withMobilePhone("+7(911)111-11-11")
                    .withLastname(String.format("test %s",i)).withHomePhone("+7(911)111-11-11").withEmail(String.format("test %s ",i + "@gmail.com")));
        }
        return contacts;

    }


}
