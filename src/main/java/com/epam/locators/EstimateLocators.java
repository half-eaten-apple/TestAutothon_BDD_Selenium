package com.epam.locators;/**/

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

public class EstimateLocators extends Components{

	@FindBy(xpath = "//a/span[text()='Estimates']")
	private WebElement eleNewEstimateTab;

	@FindBy(xpath = "//input[@title='New Estimate']")
	private WebElement eleNewEstimate;
	@FindBy(xpath = "//*[text()[contains(.,'Interaction')]]/following-sibling::td/div/a")
	private WebElement eleCreatedInteractionName;
	@FindBy(xpath = "//span[@class = 'listTitle' and text()  = 'Expenses']")
	private WebElement eleNewExpenseTab;

	@FindBy(xpath = ".//td[@id='topButtonRow']/input[@name='save']")
	private WebElement eleNewestSave;

	@FindBy(xpath = "//label[contains(text(),'Spend Type')]/parent::td/following-sibling::td//select")
	private WebElement eleNewestSpendType;

	@FindBy(xpath = "//label[text()='Amount']/parent::td/following-sibling::td[contains(@class,'dataCol')]/input")
	private WebElement eleNewestAmount;

	@FindBy(xpath = "//*[text()[contains(.,'Interaction')]]/following-sibling::td/div/a")
	private WebElement eleCreateInteractionName;

	//GETTERS
	public IButton getNewEstimateTab(){
		return getComponent(eleNewEstimateTab,ButtonImpl.class, this.getClass());
	}

	public IButton getNewEstimate(){
		return getComponent(eleNewEstimate,ButtonImpl.class, this.getClass());
	}
	public IDropDown getEstSpendTypeDropdown() {
		return getComponent(eleNewestSpendType, DropDownImpl.class, this.getClass());
	}

	public ITextField getEstAmountType() {
		return getComponent(eleNewestAmount, TextFieldImpl.class, this.getClass());
	}

	public IButton getNewEstSaveBtn(){
		return getComponent(eleNewestSave,ButtonImpl.class, this.getClass());
	}
	//SETTERS
	public void setEstAmountType(String estimationAmount) throws FrameworkExceptions {
		getEstAmountType().setText(estimationAmount);
	}

	public void setEstSpendTypeDropdown(String estimationSpendType) throws FrameworkExceptions{
		getEstSpendTypeDropdown().selectByIgnoringSpecialCharacters(estimationSpendType);
	}

	public IButton getCreatedInteractionName() {
		return getComponent(eleCreatedInteractionName, ButtonImpl.class, this.getClass());
	}
}