package com.epam.elements.impls;

import com.epam.elements.IDropDown;
import com.epam.utils.Constants;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropDownImpl extends ElementImpl implements IDropDown {
    private WebElement element = null;
    private String strExceptionText  = "DropDown Element: ";


    public DropDownImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public void selectByValue(String valueToBeSelected) throws FrameworkExceptions{
        if(isLoaded()) {
            Select select = new Select(element);
            select.selectByValue(valueToBeSelected);
            setLogger(strExceptionText + elementOriginalName + " => " + valueToBeSelected +  Constants.SELECTLOGMESSAGE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_SUCCESSS);
        } else {
            setLogger("#### " + strExceptionText + elementOriginalName + "selection failed", strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByValue()" );
        }
    }

    @Override
    public void selectByVisibleText(String valueToBeSelected) throws FrameworkExceptions{
        if(isLoaded()) {
            Select select = new Select(element);
            select.selectByVisibleText(valueToBeSelected);
            setLogger(strExceptionText + elementOriginalName + " => " + valueToBeSelected + Constants.SELECTLOGMESSAGE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_SUCCESSS);
        } else {
            setLogger("###### " + strExceptionText + elementOriginalName + "selection failed", strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByVisibleText()" );
        }
    }

    @Override
    public void selectByIndex(int valueToBeSelected) throws FrameworkExceptions{
        if(isLoaded()) {
            Select select = new Select(element);
            select.selectByIndex(valueToBeSelected);
            setLogger(strExceptionText + elementOriginalName + " => " + valueToBeSelected + Constants.SELECTLOGMESSAGE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_SUCCESSS);
        } else {
            setLogger("#### " + strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByIndex()" );
        }
    }

    @Override
    public void selectByIgnoringSpecialCharacters(String valueToBeSelected)	throws FrameworkExceptions{
        if(isLoaded()) {
            Select select = new Select(element);
            List<WebElement> optionElements = select.getOptions();

            for (WebElement optionElement : optionElements) {
                String option = optionElement.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                String partValue = valueToBeSelected.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                if (option.contains(partValue)) {
                    String optionIndex = optionElement.getAttribute("index");
                    selectByIndex(Integer.parseInt(optionIndex));
                    break;
                }
            }
            setLogger(strExceptionText + elementOriginalName + " => " + valueToBeSelected + Constants.SELECTLOGMESSAGE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_SUCCESSS);
        } else {
            setLogger("##### " + strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByIgnoringSpecialCharacters()" );
        }
    }

    @Override
    public boolean waitUntilSelectOptionsPopulated() throws FrameworkExceptions {
        if(isLoaded()) {
            boolean flag = false;
            if (Services.waitUntilSelectOptionsPopulated(element) != null){
                flag = true;
                setLogger(strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_SUCCESSS);
            }
            return flag;
        } else {
            setLogger("##### " + strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE, strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method waitUntilSelectOptionsPopulated()" );
        }
    }
}
