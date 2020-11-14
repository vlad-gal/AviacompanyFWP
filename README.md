[English](#english)
## Учебный проект по курсу Java Web Development<a name="русский"></a>
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
    * Просмотр аэропортов, в которые выполняются рейсы
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
    * Просмотр прошедших, активных и будущих рейсов, в которых они участвовали либо участвуют
    * Просмотр летных бригад в которых они участвуют
    
    
[Русский](#русский) 
## Study project for the Java Web Development course<a name="english"></a>
## Theme: System "Aviacompany"
### Author: Vladislav Halatsevich
---
### General description

  The web application is an internal airline system that allows you to register and operate flights,
  crews, aircraft, airports, as well as participants in the flight process.
  When user is registering, they select the role corresponding to their profession.
  The roles are defined in the web application:
  * Administrator (cannot be selected when user is registering)
  * Flight operator
  * Crew dispatcher 
  * Pilot
  * Navigator
  * Radioman
  * Stewardess
                    
   After registration, the user is activated using a confirmation sent to the mailbox specified during registration.
   After successful activation of the account, the user can log in to their personal account.
   Further, depending on the role, different actions are available to users.
                    
   Common functionality is provided for all roles, such as:
   * Change the language (2 languages available, English and Russian)
   * Authorization
   * Registration
   * Reset password
   * Changing personal information in the dashboard
   * View general information on pages
### Users
                    
* **Guest**  
  Unauthorized user.
                    
  Functionality:
    * View welcome page
    * View current flights
    * View airports where flights are operated
    * View the aviation fleet
                    
* **Administrator**  
  The main site administrator. Its responsibilities include adding, changing, and deleting new users, planes,
  airports, changing flights, and changing flight crews. Deletion is performed by changing the status to * inactive.*
                    
  Functionality:
    * View, add, change users
    * View and change flights
    * View and change flight crews
    * View, add, change planes
    * View, add, change airports
                    
* **Flight operator**  
  The flight operator. It is responsible for adding, changing, and deleting flights.
                    
  Functionality:
    * The selection of available flight crews
    * View available aircraft
    * View airports
    * View, add, change flights
                    
* **Crew dispatcher**  
  The crew dispatcher. Its responsibilities include adding, changing, and deleting flight crews.
                    
  Functionality:
    * View, add, change flight crews
    * The selection of available users by role
                    
* **Pilot, Navigator, Radioman, Stewardess**   
  The main part of the flight crew.
                    
  Functionality:
    * View past, active, and future flights that they have participated in or are participating in
    * View the flight teams they participate in