package com.automation.pages.ui;

public interface CartPage {

    boolean isCartPageDisplayed();


    void clickOnDelete();

    boolean isCartEmpty();

    void cartPriceBreakDown();

    void clickOnCheckOut();

    void fillInAddress();

    void clickOnContinueBtn();

    boolean isUserOnPaymentPage();

    boolean isCheckoutPageDisplayed();
}
