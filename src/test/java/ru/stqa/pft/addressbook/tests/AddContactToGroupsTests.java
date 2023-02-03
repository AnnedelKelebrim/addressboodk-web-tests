package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupsTests extends TestBase {
    @BeforeTest()
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.goTo().createContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Подготовка")
                    .withMiddleName("Данных")
                    .withLastName("Добавлениевгруппу"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Новаягруппа"));
        }
    }

    @Test
    public void testAddContactInGroups() {
        ContactData testContact = app.db().contacts().iterator().next();
        GroupData groupToAdd = app.group().groupWithoutSelectedContact(testContact);

        Groups contactGroupsBefore = testContact.getGroups();

        Contacts groupContactsBefore;
        groupContactsBefore = groupToAdd.getContacts();

        app.goTo().homePage();
        app.contact().addContactToGroup(testContact, groupToAdd);

        ContactData testContactAfter = app.db().getContactByIdFromDB(testContact.getId());
        GroupData groupAfterAdd = app.db().getGroupByIdFromDb(groupToAdd.getId());
        Groups contactGroupsAfter = testContactAfter.getGroups();
        Contacts groupContactsAfter = groupAfterAdd.getContacts();

        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupToAdd)));
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.withAdded(testContact)));

    }
}
