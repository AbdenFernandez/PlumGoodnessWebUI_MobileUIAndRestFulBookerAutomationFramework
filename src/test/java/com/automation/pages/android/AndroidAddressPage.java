package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.AddressPage;
import com.automation.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class AndroidAddressPage extends BasePage implements AddressPage {

    @FindBy(xpath = "//android.view.View[@content-desc=\"my address\"]")
    WebElement myAddress;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"add new address\"]")
    WebElement addNewAddress;
    @FindBy(xpath = "//android.view.View[@content-desc=\"enter address\"]")
    WebElement enterAddress;

    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'pincode')]")
    WebElement zip;
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"save\"]")
    WebElement save;
    @FindBy(xpath = "//android.view.View[@content-desc=\"home\"]")
    WebElement home;
    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'full')]")
    WebElement fullAddress;
    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'name')]")
    WebElement fName;

    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'phone')]")
    WebElement phone;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"Home\")]//android.widget.ImageView[3]")
    WebElement defaultAddressDltBtn;

    @FindBy(xpath = "(//android.view.View[contains(@content-desc,'Home')]/android.widget.ImageView[3])[2]")
    WebElement deleteBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"confirm\"]")
    WebElement confirmBtn;

    @Override
    public void clickDeleteBtnOfDefaultAddress() {
        defaultAddressDltBtn.click();
        confirmBtn.click();
    }

    @Override
    public boolean userCannotDelete() {
        return isDisplayed(defaultAddressDltBtn);
    }

    @Override
    public void clickConfirmDelete() {
        confirmBtn.click();
    }

    @Override
    public boolean verifyAddressDeleted() {
        pause(3000);
        return !isDisplayed(deleteBtn);
    }

    @Override
    public void clickDeleteButton() {
        deleteBtn.click();
    }

    @Override
    public boolean verifyAddressAddedSuccessfully() {
        return defaultAddressDltBtn.isDisplayed();
    }

    @Override
    public void clickOnSaveAddress() {
        waitForElementVisible(save);
        save.click();

    }

    @Override
    public void fillForm() {

        zip.click();
        zip.sendKeys(ConfigReader.getConfigValue("zip"));
        fullAddress.click();
        fullAddress.sendKeys(ConfigReader.getConfigValue("full.address"));
        scroll(enterAddress, home);
        fName.click();
        fName.sendKeys(ConfigReader.getConfigValue("first.name"));
        phone.click();
        phone.sendKeys(ConfigReader.getConfigValue("phone.no"));
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

    }

    @Override
    public boolean isAddressFormDisplyed() {
        return enterAddress.isDisplayed();
    }

    @Override
    public void clickOnAddNewAddress() {
        addNewAddress.click();
    }

    @Override
    public boolean isAddressPageDisplayed() {
        return myAddress.isDisplayed();
    }
}
