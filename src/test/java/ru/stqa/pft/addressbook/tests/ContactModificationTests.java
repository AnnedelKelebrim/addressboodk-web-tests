package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goToHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToCreateContactPage();
            app.getContactHelper().createContact(new ContactData(
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
            app.getContactHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int contactId = before.get(0).getId();
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
        app.getContactHelper().editContact(contactData);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
