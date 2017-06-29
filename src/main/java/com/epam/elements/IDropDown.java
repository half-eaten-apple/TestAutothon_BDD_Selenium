package com.epam.elements;

import com.epam.utils.FrameworkExceptions;

public interface IDropDown extends IElement {
	void selectByValue(String text) throws FrameworkExceptions;
	void selectByVisibleText(String text) throws FrameworkExceptions;
	void selectByIndex(int index) throws FrameworkExceptions;
	void selectByIgnoringSpecialCharacters(String Value) throws FrameworkExceptions;
    boolean waitUntilSelectOptionsPopulated() throws FrameworkExceptions;
}
