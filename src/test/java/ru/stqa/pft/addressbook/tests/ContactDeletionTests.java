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
            app.contact().createContact(new ContactData()
                    .withFirstName("Подготовка")
                    .withMiddleName("Данных")
                    .withLastName("Удаление")
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
