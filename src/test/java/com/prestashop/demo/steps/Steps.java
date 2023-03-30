package com.prestashop.demo.steps;

import com.prestashop.demo.blocks.modals.ProductQuickViewModal;
import com.prestashop.demo.blocks.modals.SuccessfulAddProductModal;
import com.prestashop.demo.pages.*;
import helpers.Assertions;
import io.qameta.allure.Step;

import java.util.*;
import static com.prestashop.demo.pages.BasePage.open;
import static config.interfaces.Props.props;

@SuppressWarnings({"UnusedReturnValue", "FieldMayBeFinal", "FieldCanBeLocal"})
public class Steps implements Assertions<Steps> {

    private MainPage mainPage;

    private ProductQuickViewModal productQuickViewModal;

    private ProductPage productPage;

    private SuccessfulAddProductModal successfulAddProductModal;

    private CartPage cartPage;

    private CheckoutPage checkoutPage;

    private ConfirmedOrderPage confirmedOrderPage;

    ArrayList<String> titles = new ArrayList<>();

    public Steps() {
    }

    @Step("Открываем страницу магазина")
    public Steps openStore() {
        mainPage = open(props.demoPrestashopUrl(), MainPage.class);
        return this;
    }

    @Step("Открываем Quick view выбранного товара")
    public Steps openProductQuickView(int productPosition) {
        productQuickViewModal = mainPage.openProductQuickView(productPosition);
        productQuickViewModal.getProductTitle((title) -> titles.add(title));
        return this;
    }

    public Steps openProductPage() {
        openProductPage(1);
        return this;
    }

    @Step("Открываем страницу выбранного товара")
    public Steps openProductPage(int productPosition) {
        productPage = mainPage.openProductPage(productPosition);
        productPage.getProductTitle((title) -> titles.add(title));
        return this;
    }

    @Step("Устанавливаем количество товара вводя значение в поле")
    public Steps setQuantityByInput(int quantity) {
        if (productPage == null) productQuickViewModal.clearInput().setQuantityByInput(quantity);
        else productPage.clearInput().setQuantityByInput(quantity);
        return this;
    }

    @Step("Увеличиваем количество товара по кнопке")
    public Steps increaseQuantityByButton(int quantity) {
        if (productPage == null) productQuickViewModal.increaseQuantityByButton(quantity);
        else productPage.increaseQuantityByButton(quantity);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public Steps addProductToCart() {
        successfulAddProductModal = (productPage == null) ? productQuickViewModal.addProductToCart() : productPage.addProductToCart();
        return this;
    }

    @Step("Переходим в корзину")
    public Steps goToCart() {
        cartPage = successfulAddProductModal.goToCart();
        return this;
    }

    @Step("Проверяем количество товара в корзине на совпадение с положенным туда ранее")
    public Steps checkQuantity(int productPosition,int expectedQuantity) {
        cartPage.getQuantity(productPosition, (quantity) ->
                assertEquals(expectedQuantity, quantity,
                        "Количество товара в корзине не совпадает с положенным туда ранее!"));
        return this;
    }

    @Step("Возвращаемся к покупкам")
    public Steps continueShopping() {
        mainPage = successfulAddProductModal.continueShopping();
        return this;
    }

    @Step("Проверяем что товары в корзине соответствуют положенным туда ранее")
    public Steps checkTitles() {
        titles.replaceAll(String::toUpperCase);
        Collections.sort(titles);
        cartPage.checkTitles((titlesInCart) -> assertEquals(titles, titlesInCart,
                "Товары в корзине не соответствуют заказанным"));
        return this;
    }

    @Step("Возвращаемся на главную")
    public Steps goMainPage() {
        mainPage = productPage.goMainPage();
        return this;
    }

    @Step("Переходим к оформлению заказа")
    public Steps goCheckout() {
        checkoutPage = cartPage.goCheckout();
        return this;
    }

    @Step("Заполняем персональную информацию")
    public Steps fillPersonalInformation(String gender, String firstName, String lastName, String email) {
        checkoutPage.setRadiobuttonOrCheckbox(gender)
                .setField("First name", firstName)
                .setField("Last name", lastName)
                .setField("Email", email)
                .setRadiobuttonOrCheckbox("Customer data privacy")
                .setRadiobuttonOrCheckbox("I agree to the terms and conditions and the privacy policy")
                .continueCheckout();
        return this;
    }

    @Step("Заполняем адрес")
    public Steps fillAddresses(String address, String zipCode, String city) {
        checkoutPage.setField("Address", address)
                .setField("Zip/Postal Code", zipCode)
                .setField("City", city)
                .continueCheckout();
        return this;
    }

    @Step("Заполняем информацию о доставке")
    public Steps fillShippingMethod() {
        checkoutPage.continueCheckout();
        return this;
    }

    @Step("Заполняем платёжную информацию")
    public Steps fillPayment() {
        confirmedOrderPage = checkoutPage.setRadiobuttonOrCheckbox("Pay by bank wire")
                .setRadiobuttonOrCheckbox("terms of service")
                .placeOrder();
        return this;
    }

    @Step("Проверяем создание заказа")
    public Steps checkOrderConfirmation() {
        confirmedOrderPage.checkOrderConfirmation((check) -> assertTrue(check,"Заказ не подтверждён!"));
        return this;
    }

}