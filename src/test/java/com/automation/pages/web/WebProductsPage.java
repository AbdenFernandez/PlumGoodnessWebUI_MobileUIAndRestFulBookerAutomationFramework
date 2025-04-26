package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.ProductsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebProductsPage extends BasePage implements ProductsPage {

    @FindBy(xpath = "//div[contains(@class,'st-main-content')]//div[contains(@class,'st-product-wrap')]//form//button/span[@class='quick-add-btn-text']")
    List<WebElement> addToCart;

    @FindBy(xpath = "(//span[contains(text(),'Sort by:')])[1]")
    WebElement sortText;

    @FindBy(id = "backButton")
    WebElement backBtn;

    @FindBy(xpath = "//div/h3[contains(text(),'Shop by Product Type')]")
    WebElement closeFilter;

    @FindBy(xpath = "//div/h3[contains(text(),'Price')]")
    WebElement priceContainer;

    @FindBy(xpath = "//li[@class='st-py-[0px] st-w-full st-m-0 st-block st-pl-0 st-overflow-hidden']")
    List<WebElement> priceFilter;

    @FindBy(xpath = "(//li[@class='st-py-[0px] st-w-full st-m-0 st-block st-pl-0 st-overflow-hidden']//span[3])[1]")
    WebElement priceRange;

    @FindBy(xpath = "//span[@class='new-price money st-pr-[2px] !st-font-normal st-text-[#000000] st-tracking-[0px] st-text-[12px] sm:!st-text-[18px] sm:!st-leading-[27px] st-tracking-[0px]']")
    List<WebElement> priceList;

    @FindBy(xpath = "//div[@id='cart-icon-bubble']/div/span[1]")
    WebElement cartQty;

    @FindBy(xpath = "//a[@id='cart-icon']")
    WebElement cartIcon;

    @FindBy(xpath = "//div/h3[contains(text(),'Shop by Concern')]")
    WebElement concernContainer;

    @FindBy(xpath = "//h3[contains(text(),'Shop by Concern')]/following-sibling::div//span[@class='st-font-medium sm:st-font-normal st-leading-normal']")
    List<WebElement> concernFilter;

    @FindBy(xpath = "//div[contains(@class,'st-toolbox st-hidden ')]//div[contains(@class,'st-sort-dropdown')]")
    WebElement sortBtn;

    @FindBy(xpath = "//div[contains(@class,'st-toolbox st-hidden ')]//div[contains(@class,'st-sort-dropdown')]//ul/li/span[contains(text(),'Price: Low to High')]")
    WebElement sortAscendBtn;

    @FindBy(xpath = "//div[contains(@class,'st-toolbox st-hidden ')]//div[contains(@class,'st-sort-dropdown')]//ul/li/span[contains(text(),'Price: High to Low')]")
    WebElement sortDescendBtn;

    @Override
    public boolean isProductPageDisplayed() {
        waitForElementVisible(sortText);
        return sortText.isDisplayed();
    }

    public void clickOnFirstItemAddToCart() {
        addToCart.getFirst().click();
        if (isDisplayed(backBtn))
            backBtn.click();

    }


    @Override
    public boolean isItemInsideCart() {
        return cartQty.getText().equals("1");
    }

    @Override
    public void clickOnCartIcon() {
        cartIcon.click();
    }

    @Override
    public void clickOnSortButton() {
        sortBtn.click();
    }

    @Override
    public void priceAscendingSort() {
        sortAscendBtn.click();
    }

    @Override
    public boolean isPriceSortedInAscending() {


        for (int i = 0; i < priceList.size() - 1; i++) {
            System.out.println(priceList.get(i).getText() + " " + priceList.get(i + 1).getText());
            double prev = Double.parseDouble(priceList.get(i).getText().replaceAll("[^0-9.]", ""));
            double next = Double.parseDouble(priceList.get(i + 1).getText().replaceAll("[^0-9.]", ""));


            if (prev > next) return false;
        }
        return true;

    }

    @Override
    public boolean isPriceSortedInDescending() {
        for (int i = 0; i < priceList.size() - 1; i++) {
            System.out.println(priceList.get(i).getText() + " " + priceList.get(i + 1).getText());
            double prev = Double.parseDouble(priceList.get(i).getText().replaceAll("[^0-9.]", ""));
            double next = Double.parseDouble(priceList.get(i + 1).getText().replaceAll("[^0-9.]", ""));


            if (prev < next) return false;
        }
        return true;
    }

    @Override
    public void priceDescendingSort() {
        sortDescendBtn.click();
    }

    public void clickOnPriceFilter() {
        pause(3000);
        closeFilter.click();
        waitForElementClickable(priceContainer);
        priceContainer.click();
        priceFilter.getFirst().click();
    }

    public boolean verifyPriceFilterFunctionality() {

        if (priceRange == null || priceList.isEmpty()) {
            throw new AssertionError("Price range or product prices are not available.");
        }

        String rangeText = priceRange.getText().replaceAll("[^0-9,]", "").replace(",", "");
        String[] range = rangeText.split("\s*");

        if (range.length < 2) {
            throw new AssertionError("Invalid price range format: " + rangeText);
        }

        double minPrice = Double.parseDouble(range[0].trim());
        double maxPrice = Double.parseDouble(range[1].trim());

        for (WebElement priceElement : priceList) {
            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            if (!priceText.isEmpty()) {
                double productPrice = Double.parseDouble(priceText);
                if (productPrice < minPrice || productPrice > maxPrice) {
                    return false;
                }
            }
        }

        return true;
    }

    public void clickOnConcernFilter() {
        pause(3000);
        closeFilter.click();
        waitForElementClickable(concernContainer);
        concernContainer.click();
        concernFilter.getFirst().click();
    }

}
