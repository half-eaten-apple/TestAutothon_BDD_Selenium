package com.epam.elements.impls;/**/

import com.epam.elements.IElement;
import com.epam.utils.Constants;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;

public class ElementImpl implements IElement {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElementImpl.class);

    private WebElement element;
    public String elementOriginalName = "";

    public ElementImpl(){}

    public ElementImpl(WebElement element) {
        this.element = element;
    }

    public ElementImpl(WebElement element, String elementName) {
        this.element = element;
        elementOriginalName = elementName;
    }

    @Override
    public boolean isLoaded() throws FrameworkExceptions {
        try {
            boolean flag = false;
            if (Services.fluentWait(element, 60) != null) {
                flag = true;
                setLogger(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " loaded", Constants.ELEMENTLOGMESSAGE + elementOriginalName + " loaded successfully");
            } else {
                Assertion hardAssert = new Assertion();
                hardAssert.assertFalse(true, Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            }
            return flag;
        } catch (FrameworkExceptions ex) {
            setLogger(Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE, Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions("Element: " + elementOriginalName + " not loaded"+ex);
        }
    }

    @Override
    public boolean clickElement() throws FrameworkExceptions {
        if(isLoaded()) {
            element.click();
            return true;
        } else {
            setLogger(Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE, Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions("Element: " + elementOriginalName + " not loaded");
        }
    }

    @Override
    public WebElement getElement(){ return element; }

    public void setLogger(String log4j, String reportLog) {
        LOGGER.info(log4j);
        Reporter.log(reportLog);
    }

    @Override
    public String getText() throws FrameworkExceptions {
        if(isLoaded()) {
            String text = null;
            if(element.getAttribute("value") != null)
                text = element.getAttribute("value");
            else if(element.getAttribute("text") != null)
                text = element.getAttribute("text");
            else if(element.getAttribute("textContent") != null)
                text = element.getAttribute("textContent");
            setLogger(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " get text successfully", Constants.ELEMENTLOGMESSAGE + elementOriginalName + " get text successfully");
            return text;
        } else {
            setLogger("#####"+ Constants.ELEMENTLOGMESSAGE + elementOriginalName + " failed to get text", Constants.ELEMENTLOGMESSAGE + elementOriginalName + " failed to get text");
            throw new FrameworkExceptions("Element Element: " + elementOriginalName + " not loaded in method getText()");
        }
    }
}
