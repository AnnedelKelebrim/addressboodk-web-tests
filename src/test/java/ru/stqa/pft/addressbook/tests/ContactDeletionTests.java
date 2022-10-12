package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().createContactPage();
            app.contact().createContact(new ContactData(
                    "Подготовка",
                    "Данных",
                    "Удаление",
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
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        int contactId = before.get(index).getId();
        app.contact().deleteContact(contactId);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
