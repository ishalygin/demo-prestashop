package com.prestashop.demo.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.util.*;
import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс PageObject корзины
 */
@SuppressWarnings("UnusedReturnValue")
public class CartPage extends BasePage<CartPage>{

    /**
     * Метод проверяет наличие заказанных товаров в корзине
     */
    @Step("Проверяем товары в корзине")
    public CartPage checkTitles(Consumer<List<String>> saver) {
        List<String> titles = $$x("//div[@class='product-line-info']/a").texts();
        titles.replaceAll(String::toUpperCase);
        Collections.sort(titles);
        saver.accept(titles);
        return this;
    }

    /**
     * Метод получает количество товара на {productPosition} месте в списке
     *
     * @param productPosition      позиция выбранного товара
     */
    @Step("Получаем количество товара на {productPosition} месте в списке")
    public CartPage getQuantity(int productPosition, Consumer<Integer> saver) {
        saver.accept(Integer.parseInt(Objects.requireNonNull($x("//li[@class='cart-item'][" + productPosition+ "]//input")
                .shouldBe(Condition.visible).getValue())));
        return this;
    }

    /**
     * Метод переходит к оформлению заказа
     */
    @Step("Переходим к оформлению заказа")
    public CheckoutPage goCheckout() {
        assertFalse($x("//span[@class='no-items']").exists(), "В корзине нет товаров!");
        $x("//a[text()='Proceed to checkout']").shouldBe(Condition.visible).click();
        return page(CheckoutPage.class);
    }
}
