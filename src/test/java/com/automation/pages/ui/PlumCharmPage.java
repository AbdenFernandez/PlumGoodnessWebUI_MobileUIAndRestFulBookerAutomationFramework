package com.automation.pages.ui;

public interface PlumCharmPage {

    boolean verifyUserIsOnRewardsPage();

    void clickOnCalculateBtn();

    void userInputAmount(int amount);

    boolean verifyPriceIsBelowCartValue(int amount);
}
