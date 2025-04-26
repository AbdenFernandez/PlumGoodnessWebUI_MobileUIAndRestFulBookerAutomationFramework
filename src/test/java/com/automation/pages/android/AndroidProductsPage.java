package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.ProductsPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidProductsPage extends BasePage implements ProductsPage {

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"FILTERS\"]")
    WebElement filterBtn;

    @FindBy(xpath = "(//android.view.View[@content-desc=\"add to cart\"])[1]")
    WebElement firstProduct;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Select size')]")
    WebElement sizeElement;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='add to cart']")
    WebElement sizeAddToCart;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView")
    WebElement close;

    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc, 'view cart')]\n")
    WebElement viewCart;

    @FindBy(xpath = "//android.view.View[@content-desc='Price']")
    WebElement priceOption;

    @FindBy(xpath = "//android.widget.CheckBox[contains(@content-desc,'-')]")
    List<WebElement> priceFilter;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'reviews')]")
    List<WebElement> priceListXpath;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]")
    WebElement searchInp;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='i have']")
    WebElement iHave;

    @FindBy(xpath = "//android.view.View//android.view.View//android.view.View//android.view.View//android.view.View//android.view.View//android.view.View")
    List<WebElement> concernOption;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='looking for']")
    WebElement lookingFor;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"find product\"]")
    WebElement findProduct;

    @FindBy(xpath = "//android.widget.Button[@content-desc='apply filter']")
    WebElement applyFilter;

    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'sort by')]")
    WebElement sortButton;

    @FindBy(xpath = "//android.widget.RadioButton[@content-desc='Price (lowest first)']")
    WebElement lowestFirst;

    @FindBy(xpath = "//android.widget.RadioButton[@content-desc='Price (highest first)']")
    WebElement highestFirst;

    @Override
    public boolean isProductPageDisplayed() {
        return filterBtn.isDisplayed();
    }

    @Override
    public void clickOnFirstItemAddToCart() {
        if (isDisplayed(sizeElement)) {
            waitForElementClickable(sizeAddToCart);
            sizeAddToCart.click();
            close.click();
        } else {
            firstProduct.click();
        }
    }


    public void clickOnPriceFilter() {
        filterBtn.click();
        priceOption.click();
        String contentDesc = priceFilter.getFirst().getAttribute("content-desc");
        tap(priceFilter.getFirst()); // Use the tap method instead of click
        ConfigReader.setConfigValue("filter.option", contentDesc);
        applyFilter.click();
    }

    @Override
    public boolean verifyPriceFilterFunctionality() {

        // Get the price filter value (e.g., "0-500")
        String priceFilterValue = ConfigReader.getConfigValue("filter.option");
        System.out.println("Price Filter Value: " + priceFilterValue);

        String[] priceRange = priceFilterValue.split("-");
        double maxPrice = Double.parseDouble(priceRange[1].trim());
        System.out.println("Max Price from Filter: " + maxPrice);

        // Regular expression pattern to match prices containing the "₹" symbol
        Pattern pattern = Pattern.compile("₹\\s*(\\d+,?\\d*)");

        // Get the first product price that contains the "₹" symbol
        double firstProductPrice = -1;
        for (WebElement product : priceListXpath) {
            scroll(searchInp,product);
            String productDesc = product.getAttribute("content-desc");
            System.out.println("Product Description: " + productDesc); // Debugging information

            String[] lines = productDesc.split("\n");
            for (String line : lines) {
                if (line.trim().startsWith("₹")) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String priceText = matcher.group(1).replace(",", ""); // Extract the first price match
                        System.out.println("Matched Price Text: " + priceText); // Debugging information
                        firstProductPrice = Double.parseDouble(priceText);
                        break;
                    }
                }

            }
            if (firstProductPrice != -1) break;
        }

        // Debugging information
        System.out.println("First Product Price: " + firstProductPrice);

        // Ensure we found a valid price
        if (firstProductPrice == -1) {
            System.out.println("No valid product price found.");
            return false;
        }

        // Compare the values
        if (firstProductPrice <= maxPrice) {
            return true;
        } else {
            return false;
        }
    }


    public void clickOnConcernFilter() {
        pause(2000);
        searchInp.click();
        waitForElementClickable(iHave);
        iHave.click();
        concernOption.get(1).click();
        waitForElementClickable(lookingFor);
        lookingFor.click();
        concernOption.get(1).click();
        findProduct.click();

    }

    public boolean isConcernedProductsDisplayed() {
        return priceListXpath.getFirst().isDisplayed() || searchInp.isDisplayed();
    }

    @Override
    public boolean isItemInsideCart() {
        return viewCart.isDisplayed();
    }

    @Override
    public void clickOnCartIcon() {
        viewCart.click();
    }

    @Override
    public void clickOnSortButton() {
        sortButton.click();

    }

    @Override
    public void priceAscendingSort() {
        lowestFirst.click();
    }

    @Override
    public boolean isPriceSortedInAscending() {
        List<Double> prices = new ArrayList<>();

        // Extract prices from the product descriptions
        Pattern pattern = Pattern.compile("₹\\s*(\\d+,?\\d*)");
        for (WebElement product : priceListXpath) {
            scroll(searchInp, product);
            String productDesc = product.getAttribute("content-desc");
            String[] lines = productDesc.split("\n");
            for (String line : lines) {
                if (line.trim().startsWith("₹")) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String priceText = matcher.group(1).replace(",", "");
                        prices.add(Double.parseDouble(priceText));
                        System.out.println(prices);
                        break;
                    }
                }
            }
        }

        // Check if the prices are sorted in ascending order
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                System.out.println(prices.get(i));
                System.out.println(prices.get(i + 1));
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean isPriceSortedInDescending() {
        List<Double> prices = new ArrayList<>();

        // Extract prices from the product descriptions
        Pattern pattern = Pattern.compile("₹\\s*(\\d+,?\\d*)");
        for (WebElement product : priceListXpath) {
            String productDesc = product.getAttribute("content-desc");
            String[] lines = productDesc.split("\n");
            for (String line : lines) {
                if (line.trim().startsWith("₹")) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String priceText = matcher.group(1).replace(",", "");
                        prices.add(Double.parseDouble(priceText));
                        System.out.println(prices);
                        break;
                    }
                }
            }
            scroll(priceListXpath.get(0), priceListXpath.get(3));
        }

        // Check if the prices are sorted in ascending order
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                System.out.println(prices.get(i + 1));
                System.out.println(prices.get(i));
                return false;
            }
        }
        return true;

    }

    @Override
    public void priceDescendingSort() {
        highestFirst.click();
    }

}
