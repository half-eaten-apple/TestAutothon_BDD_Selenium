package com.epam.elements.impls;/**/

import com.epam.elements.IButton;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import org.openqa.selenium.WebElement;

public class ButtonImpl extends ElementImpl implements IButton {
    private WebElement element = null;

    public ButtonImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public void click() throws FrameworkExceptions {
        if(clickElement()) {
            setLogger("Button element: " + elementOriginalName + " clicked", "Button element: " + elementOriginalName + " clicked successfully");
        } else {
            setLogger("##### Button element: " + elementOriginalName + " click failed", "Button element: " + elementOriginalName + " click failed");
            throw new FrameworkExceptions("Button element: " + elementOriginalName + " not found");
        }
    }

    @Override
    public void clickAlert() throws FrameworkExceptions {
        if(isLoaded()) {
            element.click();
            Services.handleAlert();
            setLogger("Alert pop-up element: " + elementOriginalName + " clicked successfully", "Alert pop-up element:  " + elementOriginalName + " clicked successfully");
        } else {
            setLogger("##### Alert pop-up element: " + elementOriginalName + " click failed", "Alert pop-up element:  " + elementOriginalName + " click failed");
            throw new FrameworkExceptions("Button element: " + elementOriginalName + " not found");
        }
    }
}