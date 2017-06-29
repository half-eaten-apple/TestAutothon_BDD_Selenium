package com.epam.elements.impls;/**/

import com.epam.elements.IHyperLink;
import com.epam.utils.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public class HyperLinkImpl extends ElementImpl implements IHyperLink {
    private WebElement element = null;

    public HyperLinkImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public String getHref() throws FrameworkExceptions {
        return element.getAttribute("href");
    }

    @Override
    public String getTarget() throws FrameworkExceptions {
        return element.getAttribute("target");
    }

    @Override
    public void clickLink() throws FrameworkExceptions {
        if(clickElement()) {
            setLogger("HyperLink element: " + elementOriginalName + " clicked", "HyperLink element: " + elementOriginalName + " clicked successfully");
        }  else {
            setLogger("##### HyperLink element: " + elementOriginalName + " click failed", "HyperLink element: " + elementOriginalName + " click failed");
            throw new FrameworkExceptions("HyperLink element: " + elementOriginalName + " not found");
        }
    }
}
