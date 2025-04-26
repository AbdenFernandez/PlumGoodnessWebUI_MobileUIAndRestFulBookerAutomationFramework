package com.automation.pages.ui;

public interface AddressPage {
    void clickDeleteBtnOfDefaultAddress();

    boolean userCannotDelete();

    void clickConfirmDelete();

    boolean verifyAddressDeleted();

    void clickDeleteButton();

    boolean verifyAddressAddedSuccessfully();

    void clickOnSaveAddress();

    void fillForm();

    boolean isAddressFormDisplyed();

    void clickOnAddNewAddress();

    boolean isAddressPageDisplayed();
}
