package com.epam.elements;/**/

import com.epam.utils.FrameworkExceptions;

public interface IButton extends IElement {
	void clickAlert() throws FrameworkExceptions;
	void click() throws FrameworkExceptions;
}
