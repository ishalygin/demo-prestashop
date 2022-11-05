package com.prestashop.demo.blocks.modals;

import com.codeborne.selenide.Condition;
import com.prestashop.demo.pages.CartPage;
import com.prestashop.demo.pages.MainPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс для окна подтверждения добавления товара в корзину (модальное окно)
 */
public class SuccessfulAddProductModal {

    /**
     * Метод закрывает окно успешного добавления товаров в корзину и возвращается на прошлую страницу
     */
    @Step("Возвращаемся к покупкам")
    public MainPage continueShopping() {
        $x("//button[text()='Continue shopping']").shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

    /**
     * Метод переходит в корзину
     */
    @Step("Переходим в корзину")
    public CartPage goToCart() {
        $x("//a[text()='Proceed to checkout']").shouldBe(Condition.visible).click();
        return page(CartPage.class);
    }

}