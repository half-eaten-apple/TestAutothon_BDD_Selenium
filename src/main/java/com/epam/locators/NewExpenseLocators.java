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

public class NewExpenseLocators extends Components {

	@FindBy(xpath = "//span[@class = 'listTitle' and text()  = 'Expenses']")
	private WebElement eleNewExpEab;

	@FindBy(xpath = "//input[@title='New Expense']")
	private WebElement eleNewExp;

	@FindBy(xpath = "//label[contains(text(),'Record Type of new record')]/parent::td/following-sibling::td//select")
	private WebElement eleExpRecordType;

	@FindBy(xpath = "//input[@title='Continue']")
	private WebElement eleExpContinue;

	@FindBy(xpath = "//label[contains(text(),'Spend Type')]/parent::td/following-sibling::td//select")
	private WebElement eleExpSpendType;

	@FindBy(xpath = "//label[text()='Date of Expense']/parent::td/following-sibling::td[contains(@class,'col02')]//input")
	private WebElement eleExpDateOfExp;

	@FindBy(xpath = "//label[text()='Amount']/parent::td/following-sibling::td[contains(@class,'col02')]/div/input")
	private WebElement eleExpAmount;

	@FindBy(xpath = "//label[text()='Status']/parent::td/following-sibling::td//select")
	private WebElement eleExpStatus;

	@FindBy(xpath = ".//td[@id='topButtonRow']/input[@name='save']")
	private WebElement eleExpSave;

	@FindBy(xpath = "//label[contains(text(),'Spend Type')]/parent::td/following-sibling::td//select")
	private WebElement eleEstSpendType;

	@FindBy(xpath = "//*[text()[contains(.,'Interaction')]]/following-sibling::td/div/a")
	private WebElement eleCreateInteractionName;

	@FindBy(xpath = ".//*[@id='topButtonRow']/input[1]")
	private WebElement eleInteractionEdit;

	@FindBy(xpath = "//a/span[text()='Locations']")
	private WebElement eleLocationTab;

	@FindBy(css = "#p3")
	private WebElement eleExpense;

	//GETTERS

	public IButton getNewExpTab(){
		return getComponent(eleNewExpEab,ButtonImpl.class, this.getClass());
	}
	public IButton getNewExp(){
		return getComponent(eleNewExp,ButtonImpl.class, this.getClass());
	}
	public IDropDown getEspRecordTypeDropdown() {
		return getComponent(eleExpRecordType, DropDownImpl.class, this.getClass());
	}
	public IDropDown getEspSpendTypeDropdown() {
		return getComponent(eleExpSpendType, DropDownImpl.class, this.getClass());
	}
	public ITextField getExpDateOfExpenseType() {
		return getComponent(eleExpDateOfExp, TextFieldImpl.class, this.getClass());
	}
	public ITextField getExpAmountExpenseType() {
		return getComponent(eleExpAmount, TextFieldImpl.class, this.getClass());
	}
	public IDropDown getExpStatusDropdown() {
		return getComponent(eleExpStatus, DropDownImpl.class, this.getClass());
	}
	public IButton getNewExpSaveBtn(){
		return getComponent(eleExpSave,ButtonImpl.class, this.getClass());
	}
	public IButton getNewExpContinue(){
		return getComponent(eleExpContinue,ButtonImpl.class, this.getClass());
	}
	public IDropDown getEleExpense() {
		//return eleExpense;
		return getComponent(eleExpContinue, DropDownImpl.class, this.getClass());
	}

	//SETTERS
	public void setEspRecordTypeDropdown(String arrSelectRecordType) throws FrameworkExceptions {
		getEspRecordTypeDropdown().selectByVisibleText(arrSelectRecordType);
	}

	public void setEspSpendTypeDropdown(String arrSelectSpendType) throws FrameworkExceptions{
		getEspSpendTypeDropdown().selectByIgnoringSpecialCharacters(arrSelectSpendType);
	}

	public void setEspSpendTypeDropdown1(String arrSelectSpendType) throws FrameworkExceptions{
		getEspSpendTypeDropdown().selectByVisibleText(arrSelectSpendType);
	}

	public void setExpDateOfExpenseType(String newExDateOfExpense) throws FrameworkExceptions{
		getExpDateOfExpenseType().setText(newExDateOfExpense);
	}

	public void setExpAmountExpenseType(String newExpenseAmount) throws FrameworkExceptions{
		getExpAmountExpenseType().setText(newExpenseAmount);
	}

	public void setExpStatusDropdown(String newExpenseStatus) throws FrameworkExceptions{
		getExpStatusDropdown().selectByIgnoringSpecialCharacters(newExpenseStatus);
	}

}

