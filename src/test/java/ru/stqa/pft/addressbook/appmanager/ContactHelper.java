package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
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

    public void fillContactForm(ContactData contactData, boolean creation) {

        if (creation) {
            if (!isThereAGroupByName(contactData.getGroup())) {
                NavigationHelper app = new NavigationHelper(wd);
                app.goToGroupPage();
                new GroupHelper(wd).createGroup(new GroupData(contactData.getGroup(), "skdjfh", "ajdhfbvlksjdhv"));
                app.goToCreateContactPage();
            }
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getWho());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getCompanyAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        selectList(By.name("bday"), contactData.getBday());
        selectList(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        type(By.name("address2"), contactData.getAddress());
        type(By.name("phone2"), contactData.getPersPhone());
        type(By.name("notes"), contactData.getNotes());
    }

    public void choiceContact() {
        click(By.name("selected[]"));
    }

    public void goToEditContactPage() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("entry"));
    }

    public boolean isThereAGroupByName(String group) {
        return isElementPresent(By.xpath("//select[@name='new_group']/option[.='" + group + "']"));
    }

    public void createContact(ContactData contactData) {
        fillContactForm(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void deleteContact() {
        choiceContact();
        submitContactDeletion();
        closeAlert();
    }

    public void editContact(ContactData contactData) {
        goToEditContactPage();
        fillContactForm(contactData, false);
        submitContactEdition();
        returnToHomePage();
    }
}
