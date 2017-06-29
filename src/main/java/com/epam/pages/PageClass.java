package com.epam.pages;

import com.epam.driver.SetupSelenium;
import com.epam.elements.IElement;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataReader;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by vsharma on 17-10-2016.
 */
public class PageClass {
    private static Logger LOGGER = LoggerFactory.getLogger(PageClass.class);

    private WebDriver driver;
    Map<String, String> treeMap = null;

    PageClass() {
        driver = SetupSelenium.getDriver();
        //initTestData();
        TestDataFieldNames.readVariables();
        if( !Services.waitForPageLoadJS() )
        {
            LOGGER.error("Page did not load completely");
        } else {
            LOGGER.info("Page loaded successfully");
        }
    }

    public void initTestData() {
        try {
            treeMap = TestDataReader.getMap();
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Map is not initialized");
        }
    }

    public <T> T initElements(Class<T> clazz) {
        return PageFactory.initElements(driver, clazz);
    }

    public boolean verifyTextOfElement(IElement element, String strToBeVerified) throws FrameworkExceptions
    {
        String textOfElement = element.getText().trim();
        System.out.println(textOfElement);
        if (strToBeVerified.equalsIgnoreCase(textOfElement)) {
            LOGGER.info("Required element with text \"" + textOfElement + "\" is loaded successfully");
            return true;
        } else {
            throw new FrameworkExceptions("Required element is not loaded:\n Expected: " + strToBeVerified + "\t  Displayed: " + textOfElement);
        }
    }

    public boolean verifyPageTitle(String titleOfPage) throws FrameworkExceptions {
        String title = getTitle().trim();
        if(titleOfPage.equalsIgnoreCase(title)) {
            LOGGER.info("Page title "+titleOfPage +" verified");
            return true;
        } else {
            throw new FrameworkExceptions("Error in the page title:\n Expected: " + titleOfPage + "\t Displayed: " + title );
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
