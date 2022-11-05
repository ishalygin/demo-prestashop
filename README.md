# ФРЕЙМВОРК АВТОМАТИЗИРОВАННОГО ТЕСТИРОВАНИЯ ИНТЕРНЕТ-МАГАЗИНА demo.prestashop

Предназначен для тестирования интернет-магазина demo.prestashop, содержит в себе функционал взаимодействия с web-интерфейсом
страниц и инструменты для выполнения необходимых проверок
- - -
### Общая информация

`src/test/java/ru/vtb/at/ui/setup/WebDriverSetup.java` настройка web-драйвера\
`src/test/resources/general.properties` техданные для фрейма (версия хромдрайвера)\
`src/test/resources/webdrivers` директория для бинарника хромдрайвера\
`src/test/java/com/prestashop/demo/StoreTests.java` автотесты\
`src/main/java/com/prestashop/demo` PO и блоки\
`src/main/java/helpers/TestRunData.java` тестовые данные
- - -
### Локальный запуск

Запуск автотестов осуществляется:

1. Выполнением команды `mvn clean test -Dtest=StoreTests`

2. Ручным запуском тестов из класса `StoreTests`
- - -
### Сборка Allure отчёта

Сборка Allure отчёта осуществляется:

Выполнением команды `mvn allure:serve`
- - -