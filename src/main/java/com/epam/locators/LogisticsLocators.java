package com.epam.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.DropDownImpl;
import com.epam.elements.IButton;
import com.epam.elements.IDropDown;
import com.epam.elements.ITextField;
import com.epam.elements.impls.TextFieldImpl;

import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;

public class LogisticsLocators extends Components {

	@FindBy(xpath = "//span[@class='listTitle' and text()='Logistic Needs']")
	private WebElement eleNewLogistic;

	@FindBy(xpath = "//input[@value='New Logistic Need']")
	private WebElement eleNewLogisticNeed;

	@FindBy(xpath = "//label[text()='Logistic Need']/../following-sibling::td/select")
	private WebElement eleLogisticNeed;

	@FindBy(xpath = "//*[text()[contains(.,'Vendor')]]/parent::th/following-sibling::td[2]/descendant::input[2]")
	private WebElement eleLogisticsNeedPageVendorStatus;

	@FindBy(xpath = ".//input[@value = 'Add Row']")
	private WebElement eleLogisticsAddRow;

	@FindBy(xpath = "//*[text()[contains(.,'Delete')]]/parent::td/following-sibling::td[1]/descendant::select")
	private WebElement eleLogisticsComponents;

	@FindBy(xpath = "//*[text()[contains(.,'Delete')]]/parent::td/following-sibling::td[2]/descendant::input")
	private WebElement eleLogisticsEstCount;

	@FindBy(xpath = ".//*[@id='j_id0:logisticsId:fmvcomponents:pbTable:0:targetName']")
	private WebElement eleLogisticsVendorName;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement eleLogisticsNeedPageSave;

	@FindBy(xpath = ".//*[@id='topButtonRow']/input[1]")
	private WebElement eleInteractionEdit;

	@FindBy(xpath = "//input[@value='Save&New']")
	private WebElement eleLogisticsNeedPageSaveAndNew;

	@FindBy(xpath = "//*[text()[contains(.,'Logistic Provider')]]/ancestor::thead/following-sibling::tbody/tr/td[7]/select")
	private WebElement eleLogisticProvider;


	//GETTERS
	public IButton getNewLogisticTabBtn() {
		return getComponent(eleNewLogistic, ButtonImpl.class, this.getClass());
	}

	public IButton getNewLogisticNeedBtn() {
		return getComponent(eleNewLogisticNeed, ButtonImpl.class, this.getClass());
	}

	public IDropDown getLogisticDropdown() {
		return getComponent(eleLogisticNeed, DropDownImpl.class, this.getClass());
	}

	public IDropDown getLogisticVendorDropdown() {
		return getComponent(eleLogisticsNeedPageVendorStatus, DropDownImpl.class, this.getClass());
	}
	public IButton getNewLogisticAddRowBtn() {
		return getComponent(eleLogisticsAddRow, ButtonImpl.class, this.getClass());
	}
	public IDropDown getLogisticComponentsDropdown() {
		return getComponent(eleLogisticsComponents, DropDownImpl.class, this.getClass());
	}
	public ITextField getEStCountType() {
		return getComponent(eleLogisticsEstCount, TextFieldImpl.class, this.getClass());
	}
	public ITextField getVendorNameType() {
		return getComponent(eleLogisticsVendorName, TextFieldImpl.class, this.getClass());
	}
	public IDropDown getLogisticProviderDropdown() {
		return getComponent(eleLogisticProvider, DropDownImpl.class, this.getClass());
	}
	public IButton getNewLogisticNeedSaveBtn() {
		return getComponent(eleLogisticsNeedPageSave, ButtonImpl.class, this.getClass());
	}
	public IButton getInteractionEditBtn() {
		return getComponent(eleInteractionEdit, ButtonImpl.class, this.getClass());
	}
	public IButton getNewLogisticNeedSaveAndContinueBtn() {
		return getComponent(eleLogisticsNeedPageSaveAndNew, ButtonImpl.class, this.getClass());
	}

	//SETTERS
	public void setLogisticDropdown(String logisticComponent) throws FrameworkExceptions {
		getLogisticDropdown().selectByIgnoringSpecialCharacters(logisticComponent);
	}

	public void setEStCountType(String logisticEstimationCount) throws FrameworkExceptions{
		getEStCountType().setText(logisticEstimationCount);
	}

	public void setVendorNameType(String logisticVendor) throws FrameworkExceptions{
		getVendorNameType().setText(logisticVendor);
	}

	public void setLogisticProviderDropdown(String logisticProvider) throws FrameworkExceptions {
		getLogisticProviderDropdown().selectByIgnoringSpecialCharacters(logisticProvider);
	}
}
