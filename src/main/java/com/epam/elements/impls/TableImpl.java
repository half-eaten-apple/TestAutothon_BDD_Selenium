package com.epam.elements.impls;/**/

import com.epam.driver.SetupSelenium;
import com.epam.elements.ITable;
import com.epam.utils.Constants;
import com.epam.utils.FrameworkExceptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TableImpl extends ElementImpl implements ITable {
    private WebElement element = null;

    public TableImpl() {}

    public TableImpl(WebElement element, String elementName) {
        super(element, elementName);
        this.element = getElement();
    }

    @Override
    public void performTableOperation(String strColumnHeader, int intNumberOfCheckboxToBeSelected) throws FrameworkExceptions {
        int clickCount = 0;
        int intHeaderColumn = 0;
        int intFindTheHeaderOccurrenceColumn = 0;
        boolean blnSelected = false;
        try {

            List<WebElement> lstHeader = findElements(By.cssSelector(".list thead th"));
            List<WebElement> lstRows = findElements(By.cssSelector(".list tbody tr"));
            if ((lstHeader.size() == 0) || (lstRows.size() == 0)) {
                //LOGGER.error("Check the Header of the table/Rows, might be having zero values");
                Assert.fail("Check the Header of the table/Rows, might be having zero values");
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
                        //LOGGER.info("Selected " + intNumberOfCheckboxToBeSelected + " checkboxes");
                        blnSelected = true;
                        break;
                    }
                }

                if (!blnSelected) {
                    //LOGGER.error("Failed to select " + intNumberOfCheckboxToBeSelected + " checkboxes");
                    Assert.fail("Failed to select " + intNumberOfCheckboxToBeSelected + " checkboxes");
                }
            }

        } catch (Exception e) {
            //LOGGER.error("Exception at editTable");
            Assert.fail("Exception at editTable");
        }
    }

    @Override
    public void performTableActions(String strTableHeader, String strColumnHeader, String strValueToBeNavigated, String strActionToBePerformed) throws FrameworkExceptions {
        int intHeaderColumn = 0;
        int intFindTheHeaderOccurrenceColumn = 0;
        boolean blnClicked = false;

        try {
            // Generating the xpath, according to the parameter
            String xpathForFindingHeader = Constants.GET_TABLE_INTERACTION_PAGE.replace("Participants", strTableHeader);
            List<WebElement> lstHeader = findElements(By.xpath(xpathForFindingHeader + "tr[@class='headerRow']/th"));
            List<WebElement> lstRows =   findElements(By.xpath(xpathForFindingHeader + "tr"));

            if( (lstHeader.size() == 0) || (lstRows.size() == 0) ) {
                //LOGGER.error("Check the Header of the table/Rows, might be having zero values");
                Assert.fail("Check the Header of the table/Rows, might be having zero values");
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
                                //LOGGER.info("Successfully clicked on "+strActionToBePerformed);
                                blnClicked = true;
                                break;
                            }
                        }
                    }
                    if (blnClicked) break;
                }
                if (!blnClicked){
                    //LOGGER.error("Failed to click on "+strActionToBePerformed);
                    Assert.fail("Failed to click on "+strActionToBePerformed);
                }

            }
        }  catch (Exception e) {
            //LOGGER.error("Exception at editTable");
            Assert.fail("Exception at editTable");
        }
    }

    /*private List<WebElement> getTableOrSearchableHeader() {
        return findElements(By.cssSelector(".list thead th"));
    }

    private List<WebElement> getTableOrSearchableRow() {
        return findElements(By.cssSelector(".list tbody tr"));
    }

    private List<WebElement> getTableHeaderForXpath(String xpath) {
        return findElements(By.xpath(xpath + "tr[@class='headerRow']/th"));
    }*/

    private List<WebElement> findElements(By by) {
        return SetupSelenium.getDriver().findElements(by);
    }
}
