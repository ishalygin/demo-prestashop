package com.prestashop.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.interfaces.Props;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс настройки веб-драйвера
 */
@SuppressWarnings("FieldMayBeFinal")
public abstract class WebDriverSetup {

    protected static Props props = Props.props;
    protected static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static ChromeOptions options = new ChromeOptions();
    private static Map<String, Object> chromePreferences = new HashMap<>();

    public static void driverSetup() {
        options.setExperimentalOption("prefs", chromePreferences);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = props.demoPrestashopUrl();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 60000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

}