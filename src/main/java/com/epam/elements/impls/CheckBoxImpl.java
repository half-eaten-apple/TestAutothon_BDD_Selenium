package com.epam.elements.impls;/**/

import com.epam.elements.ICheckBox;
import com.epam.utils.FrameworkExceptions;

import org.openqa.selenium.WebElement;

public class CheckBoxImpl extends ElementImpl implements ICheckBox {
    private WebElement element = null;

    public CheckBoxImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public void check() throws FrameworkExceptions {
        if(isLoaded()) {
            element.click();
            setLogger("Checkbox element: " + elementOriginalName + " checked", "Checkbox element: " + elementOriginalName + " checked successfully");
        } else {
            setLogger("##### Checkbox element: " + elementOriginalName + " click failed", "Checkbox element: " + elementOriginalName + " click failed");
            throw new FrameworkExceptions("Checkbox Element: " + elementOriginalName + " not loaded");
        }
    }
}
