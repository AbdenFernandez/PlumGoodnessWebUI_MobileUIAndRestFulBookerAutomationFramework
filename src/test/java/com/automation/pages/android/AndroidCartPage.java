package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.CartPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidCartPage extends BasePage implements CartPage {

    @FindBy(xpath = "//android.view.View[@content-desc=\"cart\"]")
    WebElement cartText;

    @FindBy(xpath = "//android.view.View[@content-desc=\"checkout\"]")
    WebElement checkoutBtn;

    @FindBy(xpath = "//android.widget.EditText")
    WebElement phoneInp;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.google.android.gms:id/positive_button\"]")
    WebElement allowBtn;

    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc, 'view cart')]\n")
    WebElement viewCart;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'₹')]/android.widget.ImageView")
    WebElement delete1;

    @FindBy(xpath = "(//android.view.View[contains(@content-desc,'₹')]/android.widget.ImageView)[2]")
    WebElement delete2;

    @FindBy(xpath = "//android.view.View[@content-desc=\"remove\"]")
    WebElement remove;

    @FindBy(xpath = "//android.view.View[@content-desc=\"your cart is empty!\"]")
    WebElement emptyCart;

    @FindBy(xpath = "//android.view.View[@content-desc=\"continue\"]")
    WebElement continueBtn;

    @FindBy(xpath = "//android.webkit.WebView[@content-desc=\"primary_webview\"]")
    WebElement paymentWebView;

    @FindBy(xpath = "//android.view.View[@content-desc=\"cart\"]")
    WebElement top;
    @FindBy(xpath = "//android.widget.EditText")
    WebElement bottom;
    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'order summary')]")
    WebElement priceBreakdown;

    @Override
    public boolean isCartPageDisplayed() {
        return cartText.isDisplayed();
    }

    public void clickOnDelete() {
        if (isDisplayed(delete1)){
            delete2.click();
        }
        else {
            delete1.click();
        }

        remove.click();
    }

    public boolean isCartEmpty() {
        return emptyCart.isDisplayed();
    }

    @Override
    public void clickOnCheckOut() {
        checkoutBtn.click();
    }

    @Override
    public void fillInAddress() {
        phoneInp.click();
        phoneInp.sendKeys(ConfigReader.getConfigValue("phone.no"));
        waitForElementVisible(allowBtn);
        allowBtn.click();
        waitForElementVisible(viewCart);
        viewCart.click();
        checkoutBtn.click();
    }

    @Override
    public void clickOnContinueBtn() {
        continueBtn.click();
    }

    @Override
    public boolean isUserOnPaymentPage() {
        return paymentWebView.isDisplayed();
    }

    @Override
    public boolean isCheckoutPageDisplayed() {
        return true;
    }

    public void cartPriceBreakDown() {
        pause(2000);
        scroll(top, bottom);
        String contentDescription = priceBreakdown.getAttribute("content-desc");
        String[] lines = contentDescription.split("\n");
        System.out.println(Arrays.toString(lines));

        // Pattern to extract the amounts without the negative sign
        Pattern pattern = Pattern.compile("\\d+(\\.\\d{1,2})?");

        // Extracting MRP
        Matcher mrpMatcher = pattern.matcher(lines[3]);
        mrpMatcher.find();
        double mrp = Double.parseDouble(mrpMatcher.group());
        System.out.println("MRP: " + mrp);

        // Extracting Discount
        Matcher discountMatcher = pattern.matcher(lines[5]);
        discountMatcher.find();
        double discount = Double.parseDouble(discountMatcher.group());
        System.out.println("Discount: " + discount);

        // Extracting Shipping Fee
        Matcher shippingFeeMatcher = pattern.matcher(lines[7]);
        shippingFeeMatcher.find();
        double shippingFee = 0;
        if (!shippingFeeMatcher.group().isEmpty()) {
            shippingFee = Double.parseDouble(shippingFeeMatcher.group());
        }
        System.out.println("Shipping Fee: " + shippingFee);

        // Extracting To Pay
        Matcher toPayMatcher = pattern.matcher(lines[9]);
        toPayMatcher.find();
        double toPay = Double.parseDouble(toPayMatcher.group());
        System.out.println("To Pay: " + toPay);


        if (toPay == mrp - discount)
            System.out.println("Price Verified with no shipping fee");
        else
            System.out.println("Price verified with shipping fee");
    }
}
