package com.epam.utils;

import java.util.List;

import com.epam.driver.SetupSelenium;
import com.epam.locators.InteractionPageLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.epam.locators.ProductTopicLocators;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CommonFunctions {
	private static final Logger LOGGER = Logger.getLogger(CommonFunctions.class);

    private ProductTopicLocators getPage() { return PageFactory.initElements(SetupSelenium.getDriver(), ProductTopicLocators.class); }
    private InteractionPageLocators getInteractionLocatorsPage() { return PageFactory.initElements(SetupSelenium.getDriver(), InteractionPageLocators.class); }

	public void performTableOperation(String strColumnHeader, int intNumberOfCheckboxToBeSelected) {
		int clickCount = 0;
		int intHeaderColumn = 0;
		int intFindTheHeaderOccurrenceColumn = 0;
		boolean blnSelected = false;
		try {

			List<WebElement> lstHeader = getPage().getTableOrSearchableHeader();
			List<WebElement> lstRows = getPage().getTableOrSearchableRow();
			if ((lstHeader.size() == 0) || (lstRows.size() == 0)) {
				LOGGER.error("Check the Header of the table/Rows, might be having zero values");
			}

			if (lstHeader.size() > 0) {
				for (WebElement elt : lstHeader) {
					if (elt.getText().equalsIgnoreCase(strColumnHeader)) {
						intHeaderColumn = intFindTheHeaderOccurrenceColumn;
						break;
					}
					intFindTheHeaderOccurrenceColumn++;
				}
				int intCounter = 1;
				for (WebElement elt : lstRows) {
					List<WebElement> lstTD = elt.findElements(By.cssSelector("td"));
					WebElement we = lstTD.get(intHeaderColumn);

					String strXpath = Constants.PERFORM_TABLE_ACTIONS_XPATH + intCounter + "]";
					/*((JavascriptExecutor) SetupSelenium.getDriver()).executeScript("arguments[0].scrollIntoView(true);", we);
					Thread.sleep(3000);*/
					if (!we.findElement(By.xpath(strXpath)).isSelected()) {
						we.findElement(By.xpath(strXpath)).click();
						clickCount++;
					}
					intCounter++;
					if (clickCount == intNumberOfCheckboxToBeSelected) {

						LOGGER.info("Selected " + intNumberOfCheckboxToBeSelected + " checkboxes");
						blnSelected = true;
						break;
					}
				}

				if (!blnSelected) {
					LOGGER.error("Failed to select " + intNumberOfCheckboxToBeSelected + " checkboxes");
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception at editTable");
		} /*finally {
			((JavascriptExecutor) SetupSelenium.getDriver()).executeScript("window.scrollBy(0,-250)", "");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}


	/**
	 *  Function clicks the link in the table according to the parameter passed
	 *  @param strTableHeader header of the table in which user want to do these particular actions
	 *                        eg: Participant
	 *  @param strColumnHeader column header of the table, where you want to select the checkbox
	 *                         eg: Role header of the Participant table
	 *  @param strValueToBeNavigated value of the particular row where you want to perform your action
	 *                               eg: collaborator in the Participant table
	 *  @param strActionToBePerformed what kind action to be done in that particular row, which is choosen by the above criterias
	 *                                eg: edit, delete
	 */
	public void performTableActions(String strTableHeader, String strColumnHeader, String strValueToBeNavigated, String strActionToBePerformed) {

		int intHeaderColumn = 0;
		int intFindTheHeaderOccurrenceColumn = 0;
		boolean blnClicked = false;

		try {
			// Generating the xpath, according to the parameter
			String xpathForFindingHeader = Constants.GET_TABLE_INTERACTION_PAGE.replace("Participants", strTableHeader);
			List<WebElement> lstHeader = SetupSelenium.getDriver().findElements(By.xpath(xpathForFindingHeader + "tr[@class='headerRow']/th"));
			List<WebElement> lstRows =   SetupSelenium.getDriver().findElements(By.xpath(xpathForFindingHeader + "tr"));

			if( (lstHeader.size() == 0) || (lstRows.size() == 0) ) {
				LOGGER.error("Check the Header of the table/Rows, might be having zero values");
			}

			if (lstHeader.size() > 0) {
				// Finding the column name where user wanna search, it works according to the parameter passed
				for (WebElement elt : lstHeader) {
					if (elt.getText().equalsIgnoreCase(strColumnHeader)) {
						intHeaderColumn = intFindTheHeaderOccurrenceColumn;
						break;
					}
					intFindTheHeaderOccurrenceColumn++;
				}

				// Second row is the where the rows starting, first one contains header
				for (int i = 2; i <= lstRows.size(); i++) {
					// List starts with 0 element
					WebElement elt = lstRows.get(i - 1);
					String strXpath = xpathForFindingHeader + "tr[" + i + "]/child::node()";
					List<WebElement> lstTD = elt.findElements(By.xpath(strXpath));
					// Gets the header column row element
					WebElement we = lstTD.get(intHeaderColumn);
					if (strValueToBeNavigated.equalsIgnoreCase(we.getText())) {
						List<WebElement> lstAnchorNodesInsideSpecifiedRow = we.findElements(By.xpath("parent::tr//a"));
						for (WebElement weAnchorTags : lstAnchorNodesInsideSpecifiedRow) {
							if (weAnchorTags.getText().toLowerCase().equalsIgnoreCase(strActionToBePerformed)) {
								weAnchorTags.click();
								LOGGER.info("Successfully clicked on "+strActionToBePerformed);
								blnClicked = true;
								break;
							}
						}
					}
					if (blnClicked) break;                   
				}
				if (!blnClicked){
					LOGGER.error("Failed to click on "+strActionToBePerformed);
				}

			}
		}  catch (Exception e) {
			LOGGER.error("Exception at editTable");
		}
	}

	public void validateErrorMessage(String ErrorMessage) throws FrameworkExceptions
	{
		try {
			if(ErrorMessage != null && ErrorMessage.length() != 0 ){
				if(getInteractionLocatorsPage().getVerifyErrorMessage().getText().toLowerCase().contains(ErrorMessage.toLowerCase()))
				{
					LOGGER.info(getInteractionLocatorsPage().getVerifyErrorMessage());
				}
				else
				{
                    LOGGER.error(getInteractionLocatorsPage().getVerifyErrorMessage());
                    throw new FrameworkExceptions("Validation of error message failed.");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred at method validateErrorMessage");
            throw new FrameworkExceptions("Validation of error message failed: " + e);
		}
	}

	public void getMousehover(WebElement hoverelement,WebElement element){
		Actions action = new Actions(SetupSelenium.getDriver());
		action.moveToElement(hoverelement).moveToElement(element).click().build().perform();
	}
}
