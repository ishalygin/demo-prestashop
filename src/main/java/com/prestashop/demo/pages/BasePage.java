package com.prestashop.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.Assertions;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

/**
 * Абстрактный класс для функционала общего на всех PageObjects
 */
public abstract class BasePage<CurrentPage> implements Assertions<CurrentPage> {

    /**
     * Открывает систему в браузере
     * @param url указанный адресс системы
     * @param typePage принимает класс, последующего перехода по системе
     */
    @Step("Переходим по ссылке: {url}")
    public static <T> T open(String url, Class<T> typePage) {
        T page = Selenide.open(url,typePage);
        $x("//img[@alt='loading']").shouldBe(Condition.disappear);
        switchTo().frame("framelive");
        return typePage.cast(page);
    }

    /**
     * Метод возвращается на главную страницу
     */
    @Step("Возвращаемся на главную")
    public MainPage goMainPage() {
        $x("//img[@alt='PrestaShop']").shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

}