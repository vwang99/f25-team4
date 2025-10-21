# LocalHarvest Hub - Software Design 

Version 1  
Prepared by Victor Wang\
LocalHarvest Hub\
Oct 21, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Farmer](#221-actor-farmer)
    * 2.2.2 [Actor: Customer](#222-actor-customer) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  Victor Wang  |10/21     | Initial Design      |    1      |
|      |         |                     |           |
|      |         |                     |           |

## 1. Product Overview
Tether is a web-based platform designed to allow users to connect through chatrooms, subscribe to those chatrooms, and make chatrooms themselves. Users can browse chatrooms, view friends activity, 
leave reviews based on the enjoyment and experience in the chatroom, and subscribe to gain special privileges to those chatrooms. Users are also able to track chat history, manage members, 
and edit their chatrooms. This app supports a flexible one role system giving the user freedom and flexibility to use the app as just a chatter, a chatroom host, or both.


## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model](https://github.com/vwang99/f25-team4/blob/main/doc/Object%20Oriented%20Design/usecase.png)

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: User (Provider)
##### 2.2.1.1 Sign Up
A user can sign up to create their profile with their name, email, password, and phone number. Emails must be unique.
##### 2.2.1.2 Log In
A user shall be able to sign in using their registred email and password. After logging in, the user shall be directed the main chatroom page  where they see an list of chatrooms.
##### 2.2.1.3 Update Profile
A user shall be to modify their profile by going to their profile page. They can change their email, password, and chatrooms.
##### 2.2.1.4 Create Chatrooms
The User shall be able to create a new chatroom listing. They would provide a chatroom name and description. This chat will be created to be associated with only this user and can only be edited by that user.
##### 2.2.1.4 View Members Stats
A user will be able to view several statistics such as total members, chat history, and read and reply to ratings.

#### 2.2.2 Actor: User (Consumer)
##### 2.2.2.1 Sign Up
A user can sign up to create their profile with their name, email, password, and address. Emails must be unique.
##### 2.2.2.2 Log In
A user shall be able to sign in using their registred email and password. After logging in, the user shall be directed the main chatroom page  where they see an list of chatrooms.
##### 2.2.2.3 Browse Chatrooms
A user shall be able to view available chatrooms. They can do this from the home page or using a search function. They can also filter chatrooms by name, descriptions, or category.
##### 2.2.1.4 Subscribe to Tether service
Users can subscribe to the Tether service to gain extra features for their own chatrooms that they create. They can gain features like increasing chatroom member limit and special customization.
##### 2.2.1.5 Review Produce Box
A customer may write a review for a chatroom they are a part of. They will be able to rate the chatroom based on their own reasoning.

## 3. UML Class Diagram
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/class-diagram.png)
## 4. Database Schema
![UML Class Diagram](https://github.com/vwang99/f25-team4/blob/main/doc/Object%20Oriented%20Design/classdiagram.png)
