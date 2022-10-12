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
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToCreateContactPage();
        ContactData contactData = new ContactData
                ("Самуил",
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
        app.getContactHelper().createContact(contactData);
        List<ContactData> after = app.getContactHelper().getContactList();
        contactData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
