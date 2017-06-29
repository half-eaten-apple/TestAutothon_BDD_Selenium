package com.epam.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.DropDownImpl;
import com.epam.elements.IButton;
import com.epam.elements.IDropDown;

import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;

public class ParticipantPageLocators extends Components {

	@FindBy(xpath = "//a/span[text()='Participants']")
	private WebElement eleParticipantTab;

	@FindBy(xpath = "//input[@value='New Employee']")
	private WebElement eleEmployeeTab;

	@FindBy(css = ".detailList .requiredInput select")
	private WebElement eleHcpParticipantRole;

	@FindBy(xpath = "//input[@value='New HCPs']")
	private WebElement eleHcpButton;

	@FindBy(xpath = "//label[text()[contains(.,'Topics')]]/parent::th/following-sibling::td/select/option")
	private WebElement eleInteractionProductTopic;

	@FindBy(xpath = "//input[@value='Add Participant & Close']")
	private WebElement eleAddParticipantAndClose;

	@FindBy(xpath = ".//*[@id='topButtonRow']/input[1]")
	private WebElement eleInteractionEdit;

	@FindBy(xpath = "//input[@name='ahm__search_employee']")
	private WebElement eleNewEmployee;

	@FindBy(xpath = ".//input[@value = 'Search' and @type = 'button']/parent::td/descendant::input")
	private WebElement eleNewHcpPageSearch;

	@FindBy(xpath = "//a/span[text()='Locations']")
	private WebElement eleNewLocationTab;


	//GETTERS
	public IButton getParticipantTab() {
		return getComponent(eleParticipantTab, ButtonImpl.class, this.getClass());
	}

	public IButton getNewEmployeeTab() {
		return getComponent(eleEmployeeTab, ButtonImpl.class, this.getClass());
	}

	public IDropDown getNewHcpParticipantRole() {
		return getComponent(eleHcpParticipantRole, DropDownImpl.class, this.getClass());
	}

	public IButton getNewHcpTab() {
		return getComponent(eleHcpButton, ButtonImpl.class, this.getClass());
	}

	public IButton getSelectInteractionProductTopic() {
		return getComponent(eleInteractionProductTopic, ButtonImpl.class, this.getClass());
	}

	public IButton getAddParticipantAndClose() {
		return getComponent(eleAddParticipantAndClose, ButtonImpl.class, this.getClass());
	}


	public IButton getInteractionEdit() {
		return getComponent(eleInteractionEdit, ButtonImpl.class, this.getClass());
	}

	public IButton getNewEmployeeButton() {
		return getComponent(eleNewEmployee, ButtonImpl.class, this.getClass());
	}

	public IButton getNewHcpPageSearch() {
		return getComponent(eleNewHcpPageSearch, ButtonImpl.class, this.getClass());
	}

	public IButton getNewLocationTab() {
		return getComponent(eleNewLocationTab, ButtonImpl.class, this.getClass());
	}

	//SETTERS
	public void setNewHcpParticipantRole(String arrSelectParticipant) throws FrameworkExceptions {
		getNewHcpParticipantRole().selectByVisibleText(arrSelectParticipant);
	}

}

