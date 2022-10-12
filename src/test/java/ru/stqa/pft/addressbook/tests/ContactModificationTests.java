package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().createContactPage();
            app.contact().createContact(new ContactData(
                    "Подготовка",
                    "Данных",
                    "Модификация",
                    "Самоха",
                    "Писатель",
                    "Союз писателей",
                    "г.Чёртовы Кулички, д.4",
                    "488-09-94",
                    "79280398811",
                    "kulichki@mail.ru",
                    "16",
                    "November",
                    "1800",
                    "Теперь должно получиться",
                    "г. Чёртовы Кулички, д.15, кв.1",
                    "777-66-55",
                    "Давайте всё получится?"));
            app.contact().returnToHomePage();
        }
    }

    @Test
    public void testContactModification() throws Exception {

        List<ContactData> before = app.contact().list();
        int index = 0;
        int contactId = before.get(index).getId();
        ContactData contactData = (new ContactData(
                contactId,
                "Новая",
                "запчасть",
                "бифора",
                "Самоха",
                "Писатель",
                "Союз писателей",
                "г.Чёртовы Кулички, д.4",
                "488-09-94",
                "79280398811",
                "kulichki@mail.ru",
                "16",
                "November",
                "1800",
                "Теперь должно получиться",
                "г. Чёртовы Кулички, д.15, кв.1",
                "777-66-55",
                "Давайте всё получится?"));
        app.contact().modify(contactData);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
