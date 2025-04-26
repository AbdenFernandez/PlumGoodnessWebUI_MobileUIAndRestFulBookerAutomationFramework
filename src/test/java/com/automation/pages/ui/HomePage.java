package com.automation.pages.ui;

public interface HomePage {


    boolean isHomePageDisplayed();

    default void clickPopUpClose(){}

    void itemSearch(String configValue);

    boolean isLoggedIn();

    void clickOnOrderHistory();

    void clickOnLoginBtn();

    default void changeCurrency(String currency){}

    default boolean verifyCurrencyIsNotINR(String inr){return false;}

    void clickOnPlumRewards();
}
