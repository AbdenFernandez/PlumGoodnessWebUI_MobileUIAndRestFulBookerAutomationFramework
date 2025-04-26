package com.automation.pages.ui;

public interface ProfilePage {

    boolean verifyUserIsOnProfilePage();

    void clickOnManageAddress();

    default void editProfileDetails(){}

    default void chooseGender(){}

    default void editProfileDone(){}

    default void editUserName(String configValue){}

    default boolean verifyUserEditUsername(String configValue){return false;}
}
