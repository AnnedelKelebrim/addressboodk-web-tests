package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
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

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = (new ContactData()
                .withId(modifiedContact.getId())
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
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedContact);
        before.add(contactData);
        Assert.assertEquals(before, after);
    }
}
