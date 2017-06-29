package com.epam.elements.impls;/**/

import com.epam.elements.ILabel;
import com.epam.utils.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public class LabelImpl extends ElementImpl implements ILabel {
    private WebElement element = null;

    public LabelImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public String getLabel() throws FrameworkExceptions {
        if(isLoaded()) {
            String text = element.getText();
            setLogger("Label Element: " + elementOriginalName + " returned label", "Label Element: " + elementOriginalName + " returned label successfully");
            return text;
        } else {
            setLogger("##### Label Element: " + elementOriginalName + " label not found", "Label Element: " + elementOriginalName + " label not found");
            throw new FrameworkExceptions("Label Element: " + elementOriginalName + " not loaded");
        }
    }
}
