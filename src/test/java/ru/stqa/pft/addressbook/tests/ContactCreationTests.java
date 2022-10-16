package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().createContactPage();
        ContactData contact = new ContactData()
                .withFirstName("Создание")
                .withMiddleName("Тест")
                .withLastName("Контакта")
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
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
