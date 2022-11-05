package com.prestashop.demo;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.prestashop.demo.steps.Steps;
import helpers.Assertions;
import helpers.TestRunData;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

@ExtendWith({ScreenShooterExtension.class})
public class StoreTests extends TestsSetup implements Assertions<StoreTests> {

    @ParameterizedTest(name = "[{arguments}]")
    @Epic("Добавление нескольких товаров в корзину & Оформление заказа из корзины Story")
    @DisplayName("Добавление нескольких копий товара в корзину через QuickView")
    @ArgumentsSource(TestRunData.productQuantityTestsData.class)
    void AddToCartMultipleCopiesOfProductThroughQuickView(TestRunData.productQuantityTestsData testData) {
        new Steps()
                .openStore()
                .openProductQuickView(testData.productPosition)
                .setQuantityByInput(testData.quantityByInput)
                .increaseQuantityByButton(testData.quantityByButton)
                .addProductToCart()
                .goToCart()
                .checkQuantity(testData.productPosition, testData.quantityByInput + testData.quantityByButton);
    }

    @ParameterizedTest(name = "[{arguments}]")
    @Epic("Добавление нескольких товаров в корзину & Оформление заказа из корзины Story")
    @DisplayName("Добавление нескольких копий товара в корзину через страницу товара")
    @ArgumentsSource(TestRunData.productQuantityTestsData.class)
    void AddToCartMultipleCopiesOfProductThroughProductPage (TestRunData.productQuantityTestsData testData) {
        new Steps()
                .openStore()
                .openProductPage(testData.productPosition)
                .setQuantityByInput(testData.quantityByInput)
                .increaseQuantityByButton(testData.quantityByButton)
                .addProductToCart()
                .goToCart()
                .checkQuantity(testData.productPosition, testData.quantityByInput + testData.quantityByButton);
    }

    @ParameterizedTest(name = "[{arguments}]")
    @Epic("Добавление нескольких товаров в корзину & Оформление заказа из корзины Story")
    @DisplayName("Добавление разных товаров в корзину через QuickView")
    @ArgumentsSource(TestRunData.multipleProductsTestsData.class)
    void AddToCartMultipleProductsThroughQuickView (TestRunData.multipleProductsTestsData testData) {
        new Steps()
                .openStore()
                .openProductQuickView(testData.firstProductPosition)
                .addProductToCart()
                .continueShopping()
                .openProductQuickView(testData.secondProductPosition)
                .addProductToCart()
                .goToCart()
                .checkTitles();
    }

    @ParameterizedTest(name = "[{arguments}]")
    @Epic("Добавление нескольких товаров в корзину & Оформление заказа из корзины Story")
    @DisplayName("Добавление разных товаров в корзину через страницы товаров")
    @ArgumentsSource(TestRunData.multipleProductsTestsData.class)
    void AddToCartMultipleProductsThroughProductPage (TestRunData.multipleProductsTestsData testData) {
        new Steps()
                .openStore()
                .openProductPage(testData.firstProductPosition)
                .addProductToCart()
                .continueShopping()
                .goMainPage()
                .openProductPage(testData.secondProductPosition)
                .addProductToCart()
                .goToCart()
                .checkTitles();
    }

    @ParameterizedTest(name = "[{arguments}]")
    @Epic("Добавление нескольких товаров в корзину & Оформление заказа из корзины Story")
    @DisplayName("Оформление заказа")
    @ArgumentsSource(TestRunData.placingOrderTestsData.class)
    void OrderCheckout (TestRunData.placingOrderTestsData testData) {
        new Steps()
                .openStore()
                .openProductPage()
                .addProductToCart()
                .goToCart()
                .goCheckout()
                .fillPersonalInformation(testData.gender, testData.firstName, testData.lastName, testData.email)
                .fillAddresses(testData.address, testData.zipCode, testData.city)
                .fillShippingMethod()
                .fillPayment()
                .checkOrderConfirmation();
    }
}