package com.epam.elements;/**/

import com.epam.utils.FrameworkExceptions;

public interface ITextField extends IElement {
	void setText(String text) throws FrameworkExceptions;
	String getText() throws FrameworkExceptions;
}
