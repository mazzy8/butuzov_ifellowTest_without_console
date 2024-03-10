#language: ru

Функция: Проверка параметров задачи
  Контекст: авторизация пользователя
    Дано пользователь авторизован с данными
      | username | AT4 |
      | password | Qwerty123 |

  Структура сценария: Авторизация, переход к задаче <issueName> , проверка параметров <issueStatus> и <issueFixVersion>
    Дано название задачи, тип задачи, статус задачи, версия задачи: <issueName>, <issueType>, <issueStatus>, <issueFixVersion>
    Когда открываем страницу задачи
    Тогда страница задачи имеет опеределенные статус и версию

    Примеры:
      | issueName | issueType | issueStatus | issueFixVersion |
      | TestSelenium | Task   | СДЕЛАТЬ | Version 2.0 |