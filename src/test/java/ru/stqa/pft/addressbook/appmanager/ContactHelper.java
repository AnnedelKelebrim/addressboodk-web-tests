package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    private ApplicationManager app;

    public ContactHelper(WebDriver wd, ApplicationManager app) {
        super(wd);
        this.app = app;
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactEdition() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    private void submitAddToGroup() {
        click(By.xpath("//div[@class='right']/input[@value='Add to']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        if (creation) {
            checkAndFillContactGroup(contactData);
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        if (contactData.getFirstName() != null) {
            type(By.name("firstname"), contactData.getFirstName());
        }
        if (contactData.getMiddleName() != null) {
            type(By.name("middlename"), contactData.getMiddleName());
        }
        if (contactData.getLastName() != null) {
            type(By.name("lastname"), contactData.getLastName());
        }
        if (contactData.getNickName() != null) {
            type(By.name("nickname"), contactData.getNickName());
        }
        if (contactData.getWho() != null) {
            type(By.name("title"), contactData.getWho());
        }
        if (contactData.getCompany() != null) {
            type(By.name("company"), contactData.getCompany());
        }
        if (contactData.getFirstAddress() != null) {
            type(By.name("address"), contactData.getFirstAddress());
        }
        if (contactData.getHomePhone() != null) {
            type(By.name("home"), contactData.getHomePhone());
        }
        if (contactData.getMobilePhone() != null) {
            type(By.name("mobile"), contactData.getMobilePhone());
        }
        if (contactData.getWorkPhone() != null) {
            type(By.name("work"), contactData.getWorkPhone());
        }
        if (contactData.getFirstEmail() != null) {
            type(By.name("email"), contactData.getFirstEmail());
        }
        if (Integer.parseInt(contactData.getBday()) > 0) {
            selectList(By.name("bday"), contactData.getBday());
        }
        if (contactData.getBmonth() != null) {
            selectList(By.name("bmonth"), contactData.getBmonth());
        }
        if (contactData.getByear() != null) {
            type(By.name("byear"), contactData.getByear());
        }
        if (contactData.getSecondAddress() != null) {
            type(By.name("address2"), contactData.getSecondAddress());
        }
        if (contactData.getNotes() != null) {
            type(By.name("notes"), contactData.getNotes());
        }
    }

    private void checkAndFillContactGroup(ContactData contactData) {
        String groupName = "Подготовошная";
        if (contactData.getGroups().size() == 1) {
            groupName = contactData.getGroups().iterator().next().getName();
        }
        if (app.db().groups().size() == 0 || !isThereAGroupByNameDB(groupName)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName).withHeader("Группа").withFooter("Длясоздания"));
            app.goTo().createContactPage();
        }
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupName);
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    private void selectGroupForContact(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    public void goToEditContactPage(int id) {
        click(By.xpath("//tr[@name='entry']/td[8]/a[@href='edit.php?id=" + id + "']"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void returnToThisGroupPage(String groupName) {
        click(By.linkText("group page \"" + groupName + "\""));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("entry"));
    }

    public boolean isThereAGroupByName(String group) {
        return isElementPresent(By.xpath("//select[@name='new_group']/option[.='" + group + "']"));
    }

    public boolean isThereAGroupByNameDB(String contactGroupName) {
        Groups groupsFromDB = app.db().groups();
        for (GroupData groupName : groupsFromDB) {
            if (groupName.getName().equals(contactGroupName)) {
                return true;
            }
        }
        return false;
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitContactDeletion();
        contactCache = null;
        closeAlert();
    }

    public void modify(ContactData contact) {
        goToEditContactPage(contact.getId());
        fillContactForm(contact, false);
        submitContactEdition();
        contactCache = null;
        returnToHomePage();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupForContact(group);
        submitAddToGroup();
        contactCache = null;
        returnToThisGroupPage(group.getName());

    }

    public void deleteSelectedContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        click(By.cssSelector("input[name='remove']"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("id"));
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allEmails = searchAllEmails(element).toString();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withFirstAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails);
            contactCache.add(contact);
        }

        return new Contacts(contactCache);
    }

    private static Set<String> searchAllEmails(WebElement contact) {
        Set<WebElement> allEmailsRows = new HashSet<>(contact.findElements(By.xpath("./td[5]/a")));
        Set<String> allEmails = new HashSet<>();
        for (WebElement emailRow : allEmailsRows) {
            String email = emailRow.getText();
            allEmails.add(email);
        }
        return allEmails;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        goToEditContactPage(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.cssSelector("textarea[name=\"address\"]")).getText();
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withFirstAddress(address)
                .withFirstEmail(email1)
                .withSecondEmail(email2)
                .withThirdEmail(email3);
    }

    public ContactData contactInGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        return null;
    }
}
