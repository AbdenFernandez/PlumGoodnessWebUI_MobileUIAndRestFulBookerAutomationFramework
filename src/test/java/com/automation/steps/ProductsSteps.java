package com.automation.steps;

import com.automation.pages.android.AndroidLoginPage;
import com.automation.pages.android.AndroidProductsPage;
import com.automation.pages.ui.ProductsPage;
import com.automation.pages.web.WebLoginPage;
import com.automation.pages.web.WebProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductsSteps {

    ProductsPage productsPage;

    public ProductsSteps() {
        if (System.getProperty("platform", "web").equals("web"))
            productsPage = new WebProductsPage();
        else
            productsPage = new AndroidProductsPage();
    }

    @Then("verify searched product is displayed")
    public void verify_searched_product_is_displayed() {
        Assert.assertTrue(productsPage.isProductPageDisplayed());
    }
    @Then("verify product added to cart successfully")
    public void verify_product_added_to_cart_successfully() {
        Assert.assertTrue(productsPage.isItemInsideCart());
    }

    @When("user clicks on add to cart button of first product")
    public void user_clicks_on_add_to_cart_button_of_first_product() {
        productsPage.clickOnFirstItemAddToCart();
    }
    @When("user chooses the first price filter from the list")
    public void user_chooses_the_first_price_filter_from_the_list() {
        productsPage.clickOnPriceFilter();
    }

    @Then("verify price filter is applied to the search list")
    public void verify_price_filter_is_applied_to_the_search_list() {
        Assert.assertTrue(productsPage.isProductPageDisplayed());
        Assert.assertTrue(productsPage.verifyPriceFilterFunctionality());
    }

    @When("user chooses the first concern filter from the list")
    public void user_chooses_the_first_concern_filter_from_the_list() {
        productsPage.clickOnConcernFilter();
    }

    @Then("verify concern filter is applied to the search list")
    public void verify_concern_filter_is_applied_to_the_search_list() {
        Assert.assertTrue(productsPage.isConcernedProductsDisplayed());
    }

    @When("user goes to cart page")
    public void userGoesToCartPage() {
        productsPage.clickOnCartIcon();
    }

    @When("user click on sort button")
    public void userClickOnSortButton() {
        productsPage.clickOnSortButton();
    }

    @When("the user selects the option to sort products by price in ascending order")
    public void theUserSelectsTheOptionToSortProductsByPriceInAscendingOrder() {
        productsPage.priceAscendingSort();
    }

    @Then("the application displays products sorted from the lowest to highest price")
    public void theApplicationDisplaysProductsSortedFromTheLowestToHighestPrice() {
        Assert.assertTrue(productsPage.isPriceSortedInAscending());
    }

    @When("the user selects the option to sort products by price in descending order")
    public void theUserSelectsTheOptionToSortProductsByPriceInDescendingOrder() {
        productsPage.priceDescendingSort();
    }

    @Then("the application displays products sorted from the highest to lowest price")
    public void theApplicationDisplaysProductsSortedFromTheHighestToLowestPrice() {
        Assert.assertTrue(productsPage.isPriceSortedInDescending());
    }
}
