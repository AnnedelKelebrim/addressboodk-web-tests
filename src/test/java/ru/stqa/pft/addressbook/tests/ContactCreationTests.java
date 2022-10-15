package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().createContactPage();
        ContactData contact = new ContactData()
                .withFirstName("Создание")
                .withMiddleName("Тест")
                .withLastName("Контакта")
                .withNickName("Самоха")
                .withWho("Писатель")
                .withCompany("Союз писателей")
                .withCompanyAddress("г.Чёртовы Кулички д.4")
                .withHomePhone("488-09-94")
                .withMobilePhone("79280398811")
                .withEmail("kulichki@mail.ru")
                .withBday("16")
                .withBmonth("November")
                .withByear("1800")
                .withGroup("Теперь должно получиться")
                .withAddress("г. Чёртовы Кулички д.15 кв.1")
                .withPersPhone("777-66-55")
                .withNotes("Давайте всё получится?");
        app.contact().createContact(contact);
        Set<ContactData> after = app.contact().all();
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
