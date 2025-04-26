package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.PlumCharmPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidPlumRewardsPage extends BasePage implements PlumCharmPage {
    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"charms\")]")
    WebElement charmsBtn;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"enter cart value\")]")
    WebElement cartValueBox;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"enter cart value\")]//android.widget.EditText")
    WebElement calculateInputBox;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"enter cart value\")]/android.widget.ImageView[3]")
    WebElement enterBtn;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"enter cart value\")]//android.view.View[contains(@content-desc,'₹')]")
    List<WebElement> priceElements;

    @Override
    public boolean verifyUserIsOnRewardsPage() {
        return charmsBtn.isDisplayed();
    }

    @Override
    public void clickOnCalculateBtn() {
        scroll(charmsBtn,cartValueBox);
        calculateInputBox.click();
    }

    @Override
    public void userInputAmount(int amount) {
        calculateInputBox.clear();
        calculateInputBox.sendKeys(String.valueOf(amount));
        enterBtn.click();
    }

    @Override

    public boolean verifyPriceIsBelowCartValue(int amount) {

        for (WebElement element : priceElements) {
            String contentDesc = element.getAttribute("content-desc");
            Pattern pattern = Pattern.compile("₹(\\d+)");
            Matcher matcher = pattern.matcher(contentDesc);

            if (matcher.find()) {
                int extractedPrice = Integer.parseInt(matcher.group(1));

                System.out.println("Extracted Price: " + extractedPrice);

                if (extractedPrice > amount) {
                    return false;
                }
            }
        }

        return true;
    }

}
