package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException exception) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Я чото не понял, что за формат такой - '" + format + "'?");
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {

        for (ContactData contact : contacts) {
            try (Writer writer = new FileWriter(file)) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstName(),
                        contact.getMiddleName(),
                        contact.getLastName(),
                        contact.getNickName(),
                        contact.getWho(),
                        contact.getCompany(),
                        contact.getFirstAddress(),
                        contact.getHomePhone(),
                        contact.getMobilePhone(),
                        contact.getFirstEmail(),
                        contact.getBday(),
                        contact.getBmonth(),
                        contact.getByear(),
                        contact.getGroup(),
                        contact.getSecondAddress(),
                        contact.getNotes()));
            }
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName("Vasya" + i)
                    .withMiddleName("")
                    .withLastName("Pupkin")
                    .withNickName("Самоха")
                    .withWho("Писатель")
                    .withCompany("Союз писателей")
                    .withFirstAddress("г.Чёртовы Кулички д." + i)
                    .withHomePhone("488-09-94")
                    .withMobilePhone("79280398811")
                    .withFirstEmail("kulichki@mail.ru")
                    .withBday("16")
                    .withBmonth("November")
                    .withByear("1800")
                    .withGroup("Теперь должно получиться")
                    .withSecondAddress("г. Чёртовы Кулички д.15 кв.1")
                    .withNotes("Давайте всё получится?"));
        }
        return contacts;
    }
}
