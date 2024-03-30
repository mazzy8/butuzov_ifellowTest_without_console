#language: ru
@RickAndMortyApi
Функция: Цепочка выборки данных из GET запросов
         Найти список серий заданного персонажа.
         Получить список всех персонажей последней серии.
         Получить данные последего персонажа из списка.
         Сравнить данные с данными искомого персонажа.
  @correct
  Структура сценария: Поиск и сравнение данных
    Дано имя персонажа для поиска: Morty Smith
    Когда отправляем запрос на поиск пользователя и ожидаем статус: <statusCode>
    Тогда получаем url последней серии из списка серий персонажа
    Когда отправляем запрос на получение списка персонажей из последней серии и ожидаем статус: <statusCode>
    Тогда получаем url последнего персонажа из ответа
    Когда оправляем запрос на получение данных персонажа и ожидаем статус: <statusCode>
    Тогда сверяем рассу и местонахождение искомого и полученного персонажей

    Примеры:
      | statusCode |
      | 200        |