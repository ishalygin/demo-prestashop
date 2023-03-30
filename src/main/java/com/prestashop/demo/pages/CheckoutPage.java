package com.prestashop.demo.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс PageObject Оформления заказа
 */
@SuppressWarnings("UnusedReturnValue")
public class CheckoutPage extends BasePage<CheckoutPage>{

    /**
     * Метод выбирает радиокнопку или отмечает чекбокс {fieldName}
     *
     * @param fieldName   имя радиокнопки или чекбокса
     */
    @Step("Выбираем радиокнопку или отмечаем чекбокс {fieldName}")
    public CheckoutPage setRadiobuttonOrCheckbox(String fieldName) {
        $x("//label[contains(.,'" + fieldName + "')]").shouldBe(Condition.visible).click();
        return this;
    }

    /**
     * Метод заполняет поле {fieldName} значением {fieldValue}
     *
     * @param fieldName   имя поля
     * @param fieldValue  значение поля
     */
    @Step("Заполняем поле {fieldName} значением {fieldValue}")
    public CheckoutPage setField(String fieldName, String fieldValue) {
        $x("(//label[contains(text(), '" + fieldName + "')])[1]/following-sibling::div/input")
                .shouldBe(Condition.visible).setValue(fieldValue);
        return this;
    }

    /**
     * Метод переходит к следующему этапу оформления
     */
    @Step("Продолжаем оформление")
    public CheckoutPage continueCheckout() {
        $$x("//button[contains(text(), 'Continue')]").asFixedIterable()
                .forEach(button -> {if (button.isDisplayed()) {button.click();}});
        return this;
    }

    /**
     * Метод заканчивает оформление заказа
     */
    @Step("Заканчиваем оформление")
    public ConfirmedOrderPage placeOrder() {
        $x("//button[contains(text(), 'Place order')]").shouldBe(Condition.visible).click();
        return page(ConfirmedOrderPage.class);
    }

}