package com.automation.pages.ui;

public interface ProductsPage {

    boolean isProductPageDisplayed();

    void clickOnFirstItemAddToCart();

    void clickOnPriceFilter();

    void clickOnConcernFilter();

    boolean verifyPriceFilterFunctionality();


    boolean isItemInsideCart();


    void clickOnCartIcon();

    void clickOnSortButton();

    void priceAscendingSort();

    boolean isPriceSortedInAscending();

    boolean isPriceSortedInDescending();

    void priceDescendingSort();

    default boolean isConcernedProductsDisplayed() {
        return true;
    }
}
