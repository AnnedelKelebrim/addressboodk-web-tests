package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goToHomePage();
        ContactData contact = new ContactData(
                "Самуил",
                "Яковлевич",
                "Маршак",
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
                "Давайте всё получится?");
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToCreateContactPage();
            app.getContactHelper().createContact(contact);
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        int contactId = before.get(before.size() - 1).getId();
        app.getContactHelper().editContact(new ContactData(
                contactId,
                "xb;jr",
                "Яковлевич",
                "Маршак",
                "Изменили из теста",
                "Писатель",
                "Союз писателей",
                "г.Чёртовы Кулички, д.4",
                "488-09-94",
                "79280398811",
                "kulichki@mail.ru",
                "16",
                "November",
                "1800",
                "Test1",
                "г. Чёртовы Кулички, д.15, кв.1",
                "777-66-55",
                "Давайте всё получится?"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
