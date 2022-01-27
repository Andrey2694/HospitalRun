# Автотесты UI для тестового задания на сайте http://demo.hospitalrun.io/

## Используемые технологии и инструменты
###Java,Selenium,TestNG,Maven,Allure Report,Owner,AssertJ

## Инструкция по запуску тестов

Запуск тестов происходит через консоль

Запуск в chrome

```bash
mvn test
```
Запуск в FireFox

```bash
mvn test -Dbrowser=firefox
```

### Serve report:

```bash
allure serve allure-results
```