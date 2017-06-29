package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.locators.ProductTopicLocators;
import com.epam.utils.CommonFunctions;

public class ProductTopicPage extends PageClass {
	private static final Logger LOGGER = LoggerFactory.getLogger(InteractionPage.class);

	private ProductTopicLocators getPage() { return initElements(ProductTopicLocators.class); }
	private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

	public void addProductTopic() throws FrameworkExceptions {
		try{
			Services.waitForPageLoadJS();
			getPage().getInteractionProductTopicsTab().isLoaded();
			getPage().getInteractionProductTopicsTab().clickLink();
			getPage().getProductTopicNewProductTopicBtn().click();
			if(getPage().getProductTopic().waitUntilSelectOptionsPopulated())
				getPage().setProductTopic(TestDataFieldNames.ahmProduct);
			getCommonFunctions().performTableOperation("Select", 1);
			getPage().getProductTopicSaveAndContinue().click();
			getPage().getProductTopicSaveBtn().click();
		} catch (Exception e) {
			LOGGER.error("Failed to add product topic  " + e );
			throw new FrameworkExceptions("Failed in addProductTopic() method of ProductTopicPage class");
		}
	}

	public void verifyProductTopicIsAdded() throws FrameworkExceptions {
		try{
			Services.waitForPageLoadJS();
			getPage().getInteractionProductTopicsTab().isLoaded();
			String strLinkText = getPage().getInteractionProductTopicsTab().getText();
			strLinkText = strLinkText.substring(strLinkText.indexOf("[") + 1, strLinkText.indexOf("]")).trim();
			if (Integer.parseInt(strLinkText) > 0)
			{
                LOGGER.info("Product topic is added successfully. No. of products added "+strLinkText);
			}
			else
			{
                LOGGER.error("No product topic is attached to interaction");
			}
		} catch (Exception e) {
			LOGGER.error("Failed to add product topic  " + e );
			throw new FrameworkExceptions("Failed in verifyProductTopicIsAdded() method of ProductTopicPage class");
		}
	}
	public void verifyErrorMessageForInteractionProductTopics() throws FrameworkExceptions
	{
		try{
			getPage().getInteractionProductTopicsTab().clickLink();
			getPage().getProductTopicNewProductTopicBtn().click();
			getPage().setProductTopic(TestDataFieldNames.ahmProduct);
			getPage().getProductTopic().isLoaded();
			getCommonFunctions().performTableOperation("Select", 2);
			getPage().getProductTopicSaveBtn().click();
			getCommonFunctions().validateErrorMessage(TestDataFieldNames.productTopicValidateErrorMessage);
		}
		catch (Exception e) {
			LOGGER.error("Failed to create verifyErrorMessageForInteractionProductTopics");
			throw new FrameworkExceptions("Failed in verifyErrorMessageForInteractionProductTopics() method of ProductTopicPage class");
		}
	}
}

