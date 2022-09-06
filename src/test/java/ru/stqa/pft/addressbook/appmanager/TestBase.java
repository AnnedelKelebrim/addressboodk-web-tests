package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(Browser.CHROME.toString());

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
}
