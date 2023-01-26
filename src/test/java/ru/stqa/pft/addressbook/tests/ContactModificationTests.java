package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.goTo().createContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Подготовка")
                    .withMiddleName("Данных")
                    .withLastName("Модификация")
                    .withNickName("Самоха")
                    .withWho("Писатель")
                    .withCompany("Союз писателей")
                    .withFirstAddress("г.Чёртовы Кулички д.4")
                    .withHomePhone("488-09-94")
                    .withMobilePhone("79280398811")
                    .withFirstEmail("kulichki@mail.ru")
                    .withBday("16")
                    .withBmonth("November")
                    .withByear("1800")
                    .inGroup(new Groups().iterator().next())
                    .withSecondAddress("г. Чёртовы Кулички д.15 кв.1")
                    .withNotes("Давайте всё получится?"));
            app.contact().returnToHomePage();
        }
    }

    @Test
    public void testContactModification() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = (new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Трымпымпым")
                .withMiddleName("")
                .withLastName("Жопа"))
                .withNickName("Самоха")
                .withWho("Писатель")
                .withCompany("Союз писателей")
                .withFirstAddress("г.Чёртовы Кулички д.4")
                .withHomePhone("488-09-94")
                .withMobilePhone("79280398811")
                .withFirstEmail("kulichki@mail.ru")
                .withBday("16")
                .withBmonth("November")
                .withByear("1800")
                .inGroup(new Groups().iterator().next())
                .withSecondAddress("г. Чёртовы Кулички д.15 кв.1")
                .withNotes("Давайте всё получится?");
        app.contact().modify(contact);

        assertThat(app.db().contacts().size(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
