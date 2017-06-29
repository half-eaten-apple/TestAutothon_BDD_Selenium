package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.LogisticsLocators;
import com.epam.utils.CommonFunctions;


public class LogisticsPage extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticsPage.class);

    private LogisticsLocators getPage() { return initElements(LogisticsLocators.class); }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

    public void createNewLogistics() throws  FrameworkExceptions {
        try{
            String[] arrLogisticNeedVal = TestDataFieldNames.logisticNeed.split(";");
            getPage().getNewLogisticTabBtn().click();
            getPage().getNewLogisticNeedBtn().click();
            for(int i=0;i<arrLogisticNeedVal.length;i++) {
                getPage().getLogisticDropdown().selectByVisibleText(arrLogisticNeedVal[i]);
                if (("AV").equalsIgnoreCase(arrLogisticNeedVal[i])) {
                    getCommonFunctions().performTableOperation("Select", 2);

                } else {
                    getPage().getNewLogisticAddRowBtn().isLoaded();
                    getPage().getNewLogisticAddRowBtn().click();
                    getPage().setLogisticDropdown(TestDataFieldNames.logisticComponent);
                    getPage().setEStCountType(TestDataFieldNames.logisticEstimationCount);
                    getPage().setVendorNameType(TestDataFieldNames.logisticVendor);
                    getPage().setLogisticProviderDropdown(TestDataFieldNames.logisticProvider);
                }

                if (i == arrLogisticNeedVal.length - 1) {
                    getPage().getNewLogisticNeedSaveBtn().isLoaded();
                    getPage().getNewLogisticNeedSaveBtn().click();

                } else {
                    getPage().getNewLogisticNeedSaveAndContinueBtn().isLoaded();
                    getPage().getNewLogisticNeedSaveAndContinueBtn().click();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create new logistics " + e );
            throw new FrameworkExceptions("Failed in createNewLogistics() method of LogisticsPage class");
        }
    }

    public void verifyLogisticIsAdded() throws FrameworkExceptions {
        try{
            Services.waitForPageLoadJS();
            getPage().getNewLogisticTabBtn().isLoaded();
            String strLinkText = getPage().getNewLogisticTabBtn().getText();
            strLinkText = strLinkText.substring(strLinkText.indexOf("[") + 1, strLinkText.indexOf("]")).trim();
            if (Integer.parseInt(strLinkText) > 0)
            {
                LOGGER.info("Logistic is added successfully. No. of Logistic added "+strLinkText);
            }
            else
            {
                LOGGER.error("No Logistic is added to interaction");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add Location " + e );
            throw new FrameworkExceptions("Failed in verifyLogisticIsAdded() method of LogisticsPage class");
        }
    }
}

