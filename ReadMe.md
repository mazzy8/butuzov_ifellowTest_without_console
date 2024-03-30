#### Тестовый кейс:
1. Авторизоваться в edujira.ifellow.ru
2. Перейти в проект “Test”
3. Проверить общее количество заведенных задач в проекте
4. Перейти в задачу TestSelenium и проверить привязку в
   "Исправить в версиях: - Version 2.0"
5. Создать новый баг с описанием. Перевести задачу по статусам до закрытого.

>Run tests
>```Bash
>mvn clean test
>```
>Run report
>```Bash
>mvn allure:serve
>```

**Структура проекта**  
`scr/main/java/`  
_pages:_  
BrowseProjectsPage - страница проектов  
CreateNewTaskPage - модальная форма создания задач  
DashboardPage - стартовая борда  
FilterPage - страница поиска(фильтра)  
HeaderMenuPage - меню в шапке  
IssuePage - страница задачи  
TestProjectPage - страница проекта Test
  
_utils:_  
ConfigProvider - интерфейс для переменных в jira.conf

_helpers:_  
AllureEnvironmentWriter - запись параметров тестового окружения для Allure
BrowserInfo - получение данных по браузеру
CustomAllureSelenide - настройка скриншотов тестов

`scr/test/java/`  
_hooks_  
WebHooks - параметризация  
_tests_  
JiraTest - тесты