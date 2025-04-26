package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.AddressPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

public class WebAddressPage extends BasePage implements AddressPage {

    @FindBy(xpath = "//h3[normalize-space()='saved address']")
    WebElement savedAddTxt;

    @FindBy(xpath = "//button[normalize-space()='Add a new address']")
    WebElement addNewBtn;

    @FindBy(xpath = "//h2[@id='add-address-heading']")
    WebElement addAddTxt;

    @FindBy(id = "add-address-first-name")
    WebElement fname;

    @FindBy(id = "add-address-last-name")
    WebElement lname;

    @FindBy(id = "add-address-phone")
    WebElement phone;

    @FindBy(id = "add-address-city")
    WebElement city;

    @FindBy(id = "add-address-zip")
    WebElement zip;

    @FindBy(xpath = "//button[contains(text(),'Add address')]")
    WebElement saveAddressBtn;

    @FindBy(xpath = "//li[@data-target='#prfole_address']//div[@class='content-sd-tb']")
    WebElement manageAddress;

    @FindBy(xpath = "//customer-addresses//ul/li")
    List<WebElement> AddressList;

    @FindBy(id = "loggedin-icons")
    WebElement loggedInIcon;

    @FindBy(xpath = "//p[normalize-space()='Order History']")
    WebElement orderHist;

    @FindBy(xpath = "//button[text()='Delete']")
    List<WebElement> deleteBtn;

    @Override
    public void clickDeleteBtnOfDefaultAddress() {

    }

    @Override
    public boolean userCannotDelete() {
        return false;
    }

    @Override
    public void clickConfirmDelete() {
        System.out.println("===========================>");
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        pause(3000);
        alert.accept();
        pause(3000);
    }

    @Override
    public boolean verifyAddressDeleted() {
        return !(String.valueOf(deleteBtn.size()).equals(ConfigReader.getConfigValue("no.of.address")));
    }

    @Override
    public void clickDeleteButton() {
        ConfigReader.setConfigValue("no.of.address", String.valueOf(deleteBtn.size()));
        deleteBtn.getFirst().click();
    }

    @Override
    public boolean verifyAddressAddedSuccessfully() {
        return !AddressList.isEmpty();
    }

    @Override
    public void clickOnSaveAddress() {
        saveAddressBtn.click();
        waitForElementVisible(loggedInIcon);
        loggedInIcon.click();
        orderHist.click();
        manageAddress.click();
    }

    @Override
    public void fillForm() {
        fname.click();
        fname.sendKeys(ConfigReader.getConfigValue("first.name"));

        lname.click();
        lname.sendKeys(ConfigReader.getConfigValue("last.name"));

        zip.click();
        zip.sendKeys(ConfigReader.getConfigValue("zip"));

        city.click();
        city.sendKeys(ConfigReader.getConfigValue("city"));
        phone.sendKeys(ConfigReader.getConfigValue("phone.no"));
    }

    @Override
    public boolean isAddressFormDisplyed() {
        return addAddTxt.isDisplayed();
    }

    @Override
    public void clickOnAddNewAddress() {
        addNewBtn.click();
    }

    @Override
    public boolean isAddressPageDisplayed() {
        return savedAddTxt.isDisplayed();
    }
}
