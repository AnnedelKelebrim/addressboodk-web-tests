package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
            app.contact().returnToHomePage();
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Новаягруппа"));
        }
    }

    @Test
    public void testAddContactInGroups(){
        Groups groupsBefore = app.db().groups();
        Contacts contactsBefore = app.db().contacts();
        GroupData addGroup = groupsBefore.iterator().next();
        ContactData testContact = contactsBefore.iterator().next();
        app.goTo().homePage();
        app.contact().addToGroup(testContact,addGroup);
    }
}
