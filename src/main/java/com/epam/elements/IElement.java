package com.epam.elements;/**/

import com.epam.utils.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public interface IElement {
    WebElement getElement();
    boolean clickElement() throws FrameworkExceptions;
    boolean isLoaded() throws FrameworkExceptions;
    String getText() throws FrameworkExceptions;
}
