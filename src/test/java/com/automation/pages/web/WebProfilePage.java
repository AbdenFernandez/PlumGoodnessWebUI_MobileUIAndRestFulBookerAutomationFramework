package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.ProductsPage;
import com.automation.pages.ui.ProfilePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebProfilePage extends BasePage implements ProfilePage {
    @FindBy(xpath = "//h3[normalize-space()='order history']")
    WebElement orderHistText;

    @FindBy(xpath = "//li[@data-target='#prfole_address']//div[@class='content-sd-tb']")
    WebElement manageAddress;


    @Override
    public boolean verifyUserIsOnProfilePage() {
        return orderHistText.isDisplayed();
    }

    @Override
    public void clickOnManageAddress() {
        waitForElementVisible(manageAddress);
        manageAddress.click();
    }
}
