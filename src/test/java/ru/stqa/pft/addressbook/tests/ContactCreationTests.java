package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.goTo().createContactPage();
        ContactData contactData = new ContactData()
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
        app.contact().createContact(contactData);
        List<ContactData> after = app.contact().list();
        contactData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
