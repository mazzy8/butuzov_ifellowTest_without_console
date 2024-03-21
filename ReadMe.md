### HomeWork6  

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
AllureEnvironmentWriter - запись данных тестового окружения в файл
BrowserInfo - получение данных по браузеру

`scr/test/java/`  
_hooks_  
WebHooks - параметризация  
_tests_  
JiraTest - тесты  
  
#### Тестовый кейс:
1. Авторизоваться в edujira.ifellow.ru
2. Перейти в проект “Test”
3. Проверить общее количество заведенных задач в проекте 
4. Перейти в задачу TestSelenium и проверить "статус задачи - Сделать" и привязку в
   "Исправить в версиях: - Version 2.0"
5. Создать новый баг с описанием. Перевести задачу по статусам до закрытого.
