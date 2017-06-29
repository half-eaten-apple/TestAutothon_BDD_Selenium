package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.EstimateLocators;

public class NewEstimatePage extends PageClass {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewEstimatePage.class);
	private EstimateLocators getPage() { return initElements(EstimateLocators.class); }

	public void createNewEstimate() throws FrameworkExceptions {
		try{
			getPage().getNewEstimateTab().click();
			getPage().getNewEstimate().click();
			getPage().setEstSpendTypeDropdown(TestDataFieldNames.estimationSpendType);
			getPage().setEstAmountType(TestDataFieldNames.estimationAmount);
			getPage().getNewEstSaveBtn().click();
		} catch (Exception e) {
			LOGGER.error("Failed to create new estimate " + e );
			throw new FrameworkExceptions("Failed in createNewEstimate() method of NewEstimatePage class");
		}
	}

	public void verifyEstimateIsAdded() throws FrameworkExceptions {
		try{
			Services.waitForPageLoadJS();
			getPage().getCreatedInteractionName().click();
			getPage().getNewEstimateTab().isLoaded();
			String strLinkText = getPage().getNewEstimateTab().getText();
			strLinkText = strLinkText.substring(strLinkText.indexOf("[") + 1, strLinkText.indexOf("]")).trim();
			if (Integer.parseInt(strLinkText) > 0)
			{
				LOGGER.info("Estimate is added successfully. No. of Estimate added "+strLinkText);
			}
			else
			{
				LOGGER.error("No Estimate is added to interaction");
			}
		} catch (Exception e) {
			LOGGER.error("Failed to add Location " + e );
			throw new FrameworkExceptions("Failed in verifyEstimateIsAdded() method of NewEstimatePage class");
		}
	}
}

