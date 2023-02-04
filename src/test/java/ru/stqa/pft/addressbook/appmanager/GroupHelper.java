package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.UUID;

import static ru.stqa.pft.addressbook.appmanager.TestBase.app;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void goToSelectedGroupPage(GroupData group){
        app.goTo().homePage();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        if (groupData.getName() != null) {
            type(By.name("group_name"), groupData.getName());
        } else type(By.name("group_name"), "По умолчанию №" + (int) (Math.random() * 1000));

        if (groupData.getHeader() != null) {
            wd.findElement(By.name("group_header")).clear();
            wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        }
        if (groupData.getFooter() != null) {
            wd.findElement(By.name("group_footer")).clear();
            wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
        }
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }

    public GroupData groupWithoutSelectedContact(ContactData contact) {
        Groups groups = cleanedGroupList(contact);
        groups = prepareGroupToAdd(contact, groups);
        GroupData group = groups.iterator().next();
        return group;
    }

    private Groups prepareGroupToAdd(ContactData contact, Groups groups) {
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Патамуштавсезаняты: " + UUID.randomUUID()));
            groups = cleanedGroupList(contact);
        }
        return groups;
    }

    public Groups cleanedGroupList(ContactData contact) {
        Groups groups = app.db().groups();
        groups.removeAll(contact.getGroups());
        return groups;
    }
    public GroupData searchGroupToDeletion(ContactData testContact) {
        GroupData groupToDeletion;
        if(testContact.getGroups().size()==0){
            groupToDeletion = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contact().addContactToGroup(testContact, groupToDeletion);
        }else groupToDeletion = testContact.getGroups().iterator().next();
        return groupToDeletion;
    }
}
