package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Самуил", "Яковлевич", "Маршак", "Изменили из теста", "Писатель", "Союз писателей", "г.Чёртовы Кулички, д.4", "488-09-94", "79280398811", "kulichki@mail.ru", "16", "November", "1800", "г. Чёртовы Кулички, д.15, кв.1", "777-66-55", "Давайте всё получится?"));
        app.getContactHelper().submitModification();
        app.getContactHelper().returnToHomePage();
    }
}
