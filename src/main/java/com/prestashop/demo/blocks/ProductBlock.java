package com.prestashop.demo.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.prestashop.demo.blocks.modals.SuccessfulAddProductModal;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Интерфейс для изменения количества товара и отправки в корзину
 */
@SuppressWarnings({"unchecked", "UnusedReturnValue"})
public interface ProductBlock<CurrentPage> {

    /**
     * Метод очищает поле количества товара
     */
    @Step("Очищаем поле количества товара")
    default CurrentPage clearInput() {
        $x("//input[@id='quantity_wanted']").shouldBe(Condition.visible).sendKeys(Keys.DELETE);
        return (CurrentPage) this;
    }

    /**
     * Метод увеличивает количество товара на {quantity} кликая по кнопке увеличения
     *
     * @param quantity      на какое число увеличить количество товара
     */
    @Step("Увеличиваем количество товара по кнопке на {quantity}")
    default CurrentPage increaseQuantityByButton(int quantity) {
        SelenideElement button = $x("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']")
                .shouldBe(Condition.visible);
        for(int i = 0; i < quantity; i++) {
            button.click();
        }
        return (CurrentPage) this;
    }

    /**
     * Метод устанавливает количество товара равное {quantity} вставляя число в поле
     *
     * @param quantity      желаемое количество товара
     */
    @Step("Устанавливаем количество товара вводя значение {quantity} в поле")
    default CurrentPage setQuantityByInput(int quantity) {
        $x("//input[@id='quantity_wanted']").shouldBe(Condition.visible).setValue(String.valueOf(quantity));
        return (CurrentPage) this;
    }

    /**
     * Метод получает название товара
     */
    @Step("Получаем название товара")
    default CurrentPage getProductTitle(Consumer<String> saver) {
        saver.accept($x("//div[@class='product-prices js-product-prices']/preceding-sibling::h1")
                .shouldBe(Condition.visible).getText().toUpperCase());
        return (CurrentPage) this;
    }

    /**
     * Метод добавляет товар в корзину
     */
    @Step("Добавляем товар в корзину")
    default SuccessfulAddProductModal addProductToCart() {
        $x("//button[@class='btn btn-primary add-to-cart']").shouldBe(Condition.visible).click();
        return page(SuccessfulAddProductModal.class);
    }
}