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
            app.contact().createContact(new ContactData()
                    .withFirstName("Подготовка")
                    .withMiddleName("Данных")
                    .withLastName("Модификация")
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
                    .withNotes("Давайте всё получится?"));
            app.contact().returnToHomePage();
        }
    }

    @Test
    public void testContactModification() throws Exception {

        List<ContactData> before = app.contact().list();
        int index = 0;
        int contactId = before.get(index).getId();
        ContactData contactData = (new ContactData()
                .withId(contactId)
                .withFirstName("Vasya")
                .withMiddleName("")
                .withLastName("Pupkin"))
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
        ;
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
