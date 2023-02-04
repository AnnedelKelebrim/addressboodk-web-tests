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
            app.contact().create(new ContactData());
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData());
        }
    }

    @Test
    public void testAddContactInGroup() {
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
    @Test
    public void testDeleteContactFromGroup() {
        ContactData testContact = app.db().contacts().iterator().next();
        GroupData groupToDeletion;
        groupToDeletion = app.group().searchGroupToDeletion(testContact);

        Groups contactGroupsBefore = app.db().getContactByIdFromDB(testContact.getId()).getGroups();
        Contacts groupContactsBefore = app.db().getGroupByIdFromDb(groupToDeletion.getId()).getContacts();

        app.group().goToSelectedGroupPage(groupToDeletion);
        app.contact().deleteSelectedContactFromGroup(testContact);

        ContactData testContactAfter = app.db().getContactByIdFromDB(testContact.getId());
        GroupData groupAfterDeletion = app.db().getGroupByIdFromDb(groupToDeletion.getId());
        Groups contactGroupsAfter = testContactAfter.getGroups();
        Contacts groupContactsAfter = groupAfterDeletion.getContacts();

        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(groupToDeletion)));
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(testContact)));

    }
}
