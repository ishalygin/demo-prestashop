package helpers;

import io.qameta.allure.Step;

@SuppressWarnings({"unchecked", "UnusedReturnValue"})
public interface Assertions<CurrentPage> {

    @Step("Проверить, что значение '{actual}' равно значению '{expected}'")
    default CurrentPage assertEquals(Object expected, Object actual, String message) {
        org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
        return (CurrentPage) this;
    }

    @Step("Проверить, что значение '{actual}' не равно значению '{expected}'")
    default CurrentPage assertNotEquals(Object expected, Object actual, String message) {
        org.junit.jupiter.api.Assertions.assertNotEquals(expected, actual, message);
        return (CurrentPage) this;
    }

    @Step("Проверить, что утверждение 'true'")
    default CurrentPage assertTrue(boolean check, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(check, message);
        return (CurrentPage) this;
    }

    @Step("Проверить, что утверждение 'false'")
    default CurrentPage assertFalse(boolean check, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(check, message);
        return (CurrentPage) this;
    }

    @Step("Проверить, что '{actual}' не null")
    default CurrentPage assertNotNull(Object actual, String message) {
        org.junit.jupiter.api.Assertions.assertNotNull(actual, message);
        return (CurrentPage) this;
    }

    @Step("Ошибка: {message}")
    default CurrentPage fail(String message) {
        org.junit.jupiter.api.Assertions.fail(message);
        return (CurrentPage) this;
    }
}