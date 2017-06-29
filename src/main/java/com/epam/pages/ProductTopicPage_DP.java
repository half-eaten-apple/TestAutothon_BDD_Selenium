package com.epam.pages;

import com.epam.locators.ProductTopicLocators;
import com.epam.utils.CommonFunctions;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vsharma on 01-12-2016.
 */

public class ProductTopicPage_DP extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(InteractionPage.class);

    private ProductTopicLocators getPage() { return initElements(ProductTopicLocators.class); }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

    public boolean addProductTopic(String ahmProduct) throws FrameworkExceptions {
        try{
            //Services.waitForPageLoadJS();
            getPage().getInteractionProductTopicsTab().isLoaded();
            getPage().getInteractionProductTopicsTab().clickLink();
            getPage().getProductTopicNewProductTopicBtn().click();
            if(getPage().getProductTopic().waitUntilSelectOptionsPopulated())
                getPage().setProductTopic(ahmProduct);
            getCommonFunctions().performTableOperation("Select", 1);
            getPage().getProductTopicSaveAndContinue().click();
            getPage().getProductTopicSaveBtn().click();
            return getPage().getInteractionTab().isLoaded();
        } catch (Exception e) {
            LOGGER.error("Failed to add product topic  " + e );
            throw new FrameworkExceptions("Failed in addProductTopic() method of ProductTopicPage class");
        }
    }
}