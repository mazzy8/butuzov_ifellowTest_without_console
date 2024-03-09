#language: ru

Функция: Проверка параметров задачи
  Структура сценария: Авторизация, переход к задаче <issueName> , проверка параметров <issueStatus> и <issueFixVersion>
    Дано логин, пароль, название задачи, статус задачи, версия задачи: <username>, <password>, <issueName>, <issueType>, <issueStatus>, <issueFixVersion>
    Когда авторизуемся - открываем страницу задачи <issueName>
    Тогда страница задачи имеет опеределенные <issueStatus> и <issueFixVersion>

    Примеры:
      | username | password  | issueName | issueType | issueStatus | issueFixVersion |
      | AT4      | Qwerty123 | TestSelenium | Task   | СДЕЛАТЬ | Version 2.0 |