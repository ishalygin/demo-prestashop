package com.prestashop.demo.pages;

import io.qameta.allure.Step;

import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс PageObject Оформления заказа
 */
@SuppressWarnings({"UnusedReturnValue"})
public class ConfirmedOrderPage extends BasePage<ConfirmedOrderPage>{

    /**
     * Метод проверяет наличие подтверждения заказа
     */
    @Step("Проверяем наличие подтверждения заказа")
    public ConfirmedOrderPage checkOrderConfirmation(Consumer<Boolean> saver) {
        saver.accept($x("//h3[contains(.,'Your order is confirmed')]").exists());
        return this;
    }

}