package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.CartPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebCartPage extends BasePage implements CartPage {

    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    WebElement addToCrtBtn;

    @FindBy(xpath = "(//a[@aria-label='Remove'])[1]")
    WebElement delete;

    @FindBy(xpath = "//p[contains(text(), 'Your cart is empty')]")
    WebElement empty;

    @FindBy(xpath = "//span[@class='price']/span[@class='money']")
    WebElement mrp;

    @FindBy(xpath = "//dd[@class='price__current m-0 font-bold']//span[@class='money']")
    WebElement finalAmt;

    @FindBy(xpath = "//span[@class='savings_price']/span[@class='money']")
    WebElement discount;

    @FindBy(xpath = "//span[@class='savings_price']/span-green")
    WebElement shippingFee;





    @FindBy(xpath = "//button[@onClick='onCheckoutClick(this)']")
    WebElement checkOutBtn;

    @FindBy(xpath = "//iframe[@class='gokwik-iframe']")
    WebElement iframe;

    @FindBy(id="pincode-input")
    WebElement zipInp;

    @FindBy(id="full-name")
    WebElement fullName;

    @FindBy(id="address")
    WebElement address;

    @FindBy(id="email")
    WebElement email;

    @FindBy(id="continue-button")
    WebElement continueBtn;

    @FindBy(xpath = "//span[contains(text(),'Payment Options')]")
    WebElement paymentText;

    @FindBy(id = "phone-input")
    WebElement phoneNo;

    @FindBy(xpath = "//div[contains(@class,'address-card')]")
    WebElement addressCard;



    public boolean isCartPageDisplayed(){
        return addToCrtBtn.isDisplayed();
    }

    public void clickOnDelete() {
        delete.click();
    }
    public boolean isCartEmpty(){
        return isDisplayed(empty);
    }


    @Override
    public void clickOnCheckOut() {
        checkOutBtn.click();
    }

    @Override
    public void fillInAddress() {
        driver.switchTo().frame(iframe);
        phoneNo.sendKeys(ConfigReader.getConfigValue("phone.no"));
        waitForElementVisible(addressCard);
    }

    @Override
    public void clickOnContinueBtn() {
        continueBtn.click();
    }

    @Override
    public boolean isUserOnPaymentPage() {
        return paymentText.isDisplayed() && paymentText.getText().equalsIgnoreCase("Payment Options");
    }

    @Override
    public boolean isCheckoutPageDisplayed() {
        return isDisplayed(iframe);
    }

    public void cartPriceBreakDown(){

        System.out.println("Final amt: " + finalAmt.getText().trim().split(" ")[1]);
        double total = Double.parseDouble(finalAmt.getText().trim().split(" ")[1]);
        double disc = Double.parseDouble(discount.getText().trim().split(" ")[1]);
        double totalMrp = Double.parseDouble(mrp.getText().trim().split(" ")[1]);

        if (shippingFee.getText().equals("FREE"))
            if (total == totalMrp - disc)
                System.out.println("Price Verified with no shipping fee");
            else if (total == totalMrp - disc)
                System.out.println("Price verified with shipping fee");
    }

}

    /*@FindBy(xpath = "//div[@id='cart-icon-bubble']//span[@aria-hidden='true']")
    WebElement cartQty;*/
