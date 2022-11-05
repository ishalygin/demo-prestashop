package helpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Класс тестовых данных
 */
public class TestRunData {

    /**
     * Класс для тестов с проверкой количества товаров
     */
    public static class productQuantityTestsData implements ArgumentsProvider {

        public Integer productPosition = 1;
        public Integer quantityByInput = 2;
        public Integer quantityByButton = 3;

        @Override
        public String toString() {
            return "productPosition=" + productPosition + " ● " +
                    "quantityByInput=" + quantityByInput + " ● " +
                    "quantityByButton=" + quantityByButton;
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(new productQuantityTestsData()));
        }
    }

    /**
     * Класс для тестов с проверкой добавления разных товаров
     */
    public static class multipleProductsTestsData implements ArgumentsProvider {

        public Integer firstProductPosition = 1;
        public Integer secondProductPosition = 2;

        @Override
        public String toString() {
            return "firstProductPosition=" + firstProductPosition + " ● " +
                    "secondProductPosition=" + secondProductPosition;
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(new multipleProductsTestsData()));
        }
    }

    /**
     * Класс для теста оформления заказа
     */
    public static class placingOrderTestsData implements ArgumentsProvider {

        public String gender = "Mr.";
        public String firstName = "Михаил";
        public String lastName = "Павлович";
        public String email = "terentev@pyanyi.com";
        public String address = "Красноармейская, 4";
        public String zipCode = "22505";
        public String city = "капитал прожиточного минимума";

        @Override
        public String toString() {
            return "gender=" + gender + " ● " +
                    "firstName=" + firstName + " ● " +
                    "lastName=" + lastName + " ● " +
                    "email=" + email + " ● " +
                    "address=" + address + " ● " +
                    "zipCode=" + zipCode + " ● " +
                    "city=" + city;
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(new placingOrderTestsData()));
        }
    }
}
