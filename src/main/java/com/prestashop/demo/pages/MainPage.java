package com.prestashop.demo.pages;

import com.codeborne.selenide.Condition;
import com.prestashop.demo.blocks.modals.ProductQuickViewModal;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс PageObject для главной страницы
 */
public class MainPage extends BasePage<MainPage>{

    /**
     * Метод открывает Quick view выбранного товара на {productPosition} месте в списке
     *
     * @param productPosition      позиция выбранного товара
     */
    @Step("Открываем Quick view выбранного товара на {productPosition} месте в списке")
    public ProductQuickViewModal openProductQuickView(int productPosition) {
        $x("//article[@data-id-product='"+productPosition+"']//a[@class='quick-view js-quick-view']").hover().click();
        return page(ProductQuickViewModal.class);
    }

    /**
     * Метод открывает страницу выбранного товара на {productPosition} месте в списке
     *
     * @param productPosition      позиция выбранного товара
     */
    @Step("Открываем страницу выбранного товара на {productPosition} месте в списке")
    public ProductPage openProductPage(int productPosition) {
        $x("//article[@data-id-product='"+productPosition+"']//a[@class='thumbnail product-thumbnail']")
                .shouldBe(Condition.visible).click();
        return page(ProductPage.class);
    }

}