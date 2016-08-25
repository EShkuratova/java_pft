package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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
public class GroupDataGenerator {
    @Parameter(names = "-c" , description = "Group count")
    int count;

    @Parameter(names = "-f" , description = "Относительный путь к файлу")
    String file;
    @Parameter(names = "-d" , description = "Data format")
    String format;

    public static void main(String [] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);
        switch (format) {
            case "csv":
                saveAsCsv(groups, new File(file));
                break;
            case "xml":
                saveAsxml(groups, new File(file));
                break;
            case "json":
                saveAsJson(groups,new File(file));
                break;
            default:
                System.out.println("Неизвестный формат файла");
                break;
        }
    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }


    private void saveAsxml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        //xstream.alias("group",GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();

    }


    private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(GroupData group:groups){
            writer.write(String.format("%s;%s\n",group.getName(),group.getFooter()));
        }
            writer.close();
    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for(int i=0;i<count;i++){

            groups.add(new GroupData().withName(String.format("test %s",i)).withFooter(String.format("footer %s",i)));
        }
            return groups;

    }
}
