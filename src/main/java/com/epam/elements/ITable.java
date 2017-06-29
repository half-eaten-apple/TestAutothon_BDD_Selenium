package com.epam.elements;/**/

import com.epam.utils.FrameworkExceptions;

public interface ITable extends IElement {
    void performTableOperation(String strColumnHeader, int intNumberOfCheckboxToBeSelected) throws FrameworkExceptions;
    void performTableActions(String strTableHeader, String strColumnHeader, String strValueToBeNavigated, String strActionToBePerformed) throws FrameworkExceptions;
}
