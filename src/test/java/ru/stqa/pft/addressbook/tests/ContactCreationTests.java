package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToCreateContactPage();
        app.getContactHelper().createContact(new ContactData
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
                        "Давайте всё получится?"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before+1);
    }
}
