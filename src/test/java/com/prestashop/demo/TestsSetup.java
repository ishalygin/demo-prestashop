package com.prestashop.demo;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

public abstract class TestsSetup extends WebDriverSetup {

    @BeforeAll
    protected static void beforeAllTests() {
        driverSetup();
    }

    @AfterEach
    protected void afterEachTest() {
        Selenide.clearBrowserCookies();
    }
}