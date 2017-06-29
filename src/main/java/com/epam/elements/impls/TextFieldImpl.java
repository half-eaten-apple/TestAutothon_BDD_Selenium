package com.epam.elements.impls;/**/

import com.epam.elements.ITextField;
import com.epam.utils.Constants;
import com.epam.utils.FrameworkExceptions;

import org.openqa.selenium.WebElement;

public class TextFieldImpl extends ElementImpl implements ITextField {
    private WebElement element = null;

    public TextFieldImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public void setText(String text) throws FrameworkExceptions {
        if(isLoaded()) {
            element.sendKeys(text);
            setLogger(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " => " + text + Constants.SETTEXTLOGMESSAGE, Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + Constants.SETTEXTLOGMESSAGE);
        } else {
            setLogger("##### TextField element: " + elementOriginalName + " failed to enter text", Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " failed to enter text");
            throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " not loaded in method setText()");
        }
    }

    @Override
    public String getText() throws FrameworkExceptions {
        if(isLoaded()) {
            String text = element.getText();
            setLogger(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " => " + text + Constants.SETTEXTLOGMESSAGE, Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + Constants.SETTEXTLOGMESSAGE);
            return text;
        } else {
            setLogger("##### TextField element: " + elementOriginalName + " failed to get text", Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " failed to get text");
            throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " not loaded in method getText()");
        }
    }
}
