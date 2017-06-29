package com.epam.elements;/**/

import com.epam.utils.FrameworkExceptions;

public interface IHyperLink extends IElement {
    String getHref() throws FrameworkExceptions;
    String getTarget() throws FrameworkExceptions;
    void clickLink() throws FrameworkExceptions;
}
