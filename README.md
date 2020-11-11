
## Учебный проект по курсу Java Web Development
## Тема: Система "Авиакомпания"
### Автор: Галацевич Владислав
---
### Общее описание

  Веб приложение является внутренней системой авиакомпании, которое позволяет регистрировать и оперировать рейсами, 
  летными бригадами, самолетами, аэропортами, а также участниками летного процесса.
  При регистрации пользователь выбирает роль соответствующую его профессии.
  В веб приложении определены следующие роли: 
  * Администратор (не может быть выбран при регистрации)
  * Оператор полетов
  * Диспетчер команд
  * Пилот
  * Навигатор
  * Радист
  * Стюардесса
  
  После регистрации происходит активация пользователя с помощью подтверждения отправленного на указанный при регистрации почтовый ящик.
  После успешной активации аккаунта, пользователь может войти в свой рабочий кабинет.
  Далее в зависимости от роли, пользователям доступны различные действия.
  
  Для всех ролей предусмотрены общие функциональные возможности, такие как:
   * Смена языка (на выбор доступно 2 языка, английский и русский) 
   * Авторизация
   * Регистрация
   * Сброс пароля
   * Изменение личной информации в рабочем кабинете
   * Просмотр общей информации на страницах
### Пользователи 

* **Гость**  
  Неавторизированный пользователь.
    
  Функциональные возможности:
    * Просмотр домашней страницы
    * Просмотр актуальных рейсов
    * Просмотр аэропортов в которые выполняются рейсы
    * Просмотр парка авиации
  
* **Администратор**  
  Главный администратор сайта. В его обязанности входит добавление, изменение и удаление новых пользователей, самолетов,
  аэропортов, изменение рейсов, изменение летных бригад. Удаление происходит изменением статуса на *неактивный.*
  
  Функциональные возможности:
   * Просмотр, добавление, изменение пользователей
   * Просмотр, изменение рейсов
   * Просмотр, изменение летных бригад
   * Просмотр, добавление, изменение самолетов
   * Просмотр, добавление, изменение аэропортов
    
* **Оператор полетов**  
  Оператор полетов. В его обязанности входит добавление, изменение и удаление рейсов.
    
  Функциональные возможности:
   * Просмотр доступных летных бригад
   * Просмотр доступных самолетов
   * Просмотр аэропортов
   * Просмотр, добавление, изменение рейсов
    
* **Диспетчер команд**  
  Диспетчер команд. В его обязанности входит добавление, изменение и удаление летных бригад.
    
  Функциональные возможности:
    * Просмотр, добавление, изменение летных бригад
    * Просмотр доступных пользователей по ролям
   
* **Пилот, Навигатор, Радист, Стюардесса**  
  Основной состав летной бригады.
  
  Функциональные возможности:
    * Просмотр прошедших, активных и будущих рейсов, в которых они учавствовали либо учавствуют
    * Просмотр летных бригад в которых они учавствуют