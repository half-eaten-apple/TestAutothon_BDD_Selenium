package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.Select;

import com.epam.locators.InteractionPageLocators;
import com.epam.locators.NewExpenseLocators;

public class NewExpensePage extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewExpensePage.class);

    private NewExpenseLocators getPage() { return initElements(NewExpenseLocators.class); }
    private InteractionPageLocators getPageInteraction() { return initElements(InteractionPageLocators.class); }

    public void expensesPaid() throws FrameworkExceptions {
        try{
            String[] arrSelectRecordType = TestDataFieldNames.recordTypeOfNewRecord.split(";");
            String[] arrSelectSpendType = TestDataFieldNames.newExpenseSpendType.split(";");
            for(int i=0;i<=arrSelectRecordType.length-1;i++)
            {
                getPage().getNewExpTab().click();
                getPage().getNewExp().click();

                Select select = new Select(getPage().getEspRecordTypeDropdown().getElement());
                if(!arrSelectRecordType[i].toLowerCase().equalsIgnoreCase("Fee For Service".toLowerCase())) {
                    select.selectByVisibleText(arrSelectRecordType[i]);
                }

                getPage().getNewExpContinue().click();
                if (("Exhibit Fees").equalsIgnoreCase(arrSelectSpendType[i])){
                    getPage().setEspSpendTypeDropdown(arrSelectSpendType[i]);}
                else{
                    getPage().setEspSpendTypeDropdown1(arrSelectSpendType[i]);
                }
                getPage().setExpDateOfExpenseType(TestDataFieldNames.newExDateOfExpense);
                getPage().setExpAmountExpenseType(TestDataFieldNames.newExpenseAmount);
                getPage().setExpStatusDropdown(TestDataFieldNames.newExpenseStatus);
                getPage().getNewExpSaveBtn().click();
                getPageInteraction().getCreatedInteractionName().click();
                getPage().getNewExpTab().isLoaded();
            }
        } catch (Exception e) {
            LOGGER.error("Failed to paid the Expenses " + e );
            throw new FrameworkExceptions("Failed in expensesPaid() method of NewExpensePage class");
        }
    }

}
