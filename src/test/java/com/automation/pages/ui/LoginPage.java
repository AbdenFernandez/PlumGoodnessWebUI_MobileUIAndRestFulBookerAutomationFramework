package com.automation.pages.ui;

public interface LoginPage {
//    static boolean verifyOtpFieldIsNotDisplayed() {
//        return false;
//    }

    void openApplication();

    default boolean isLoginPageDisplayed(){return true;}

    default void verification(String configValue){}

    default void enterUsernameAndEmail(String configValue, String configValue1){}

    default void genderInput(String configValue1){}

    default void skipCLick(){}

    void clickOnLoginBtn();

    boolean verifyLoginPopUpIsDisplayed();

    void userEnterPhoneNumber(String number);

    boolean verifyOtpPageIsDisplayed();


}
