# Software Requirements Specification
## For Video Game Social Platforms

Version 0.1  
Prepared by Jesus Jut Lopez 
UNCG 
9/17/2025 

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  Jesus    |     9/17    |          Initial SRS           |      1.0     |
|   Victor   |    9/18     |           Edits Sec# 1-2          |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this Software Requirements Document (SRD) is to describe the client-view and developer-view requirements for the Tether application.
Client-oriented requirements describe the system from the client’s perspective. These requirements include a description of the different types of users served by the system.
Developer-oriented requirements describe the system from a software developer’s perspective. These requirements include a detailed description of functional, data, performance, and other important requirements.
### 1.2 Product Scope
The purpose of the Tether platform is to connect players all over the world to chat and find others playing the same game as them in a convenient and smooth manner. The system is a web-based application to produce management functions. We will have a server for all the chat rooms. We aim to connect those who have a hard time finding others with the same interest in games.
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations                                                                              
| Reference | Definition    |
| ---- | ------- |
|   Java   |    A programming language originally developed by James Gosling at Sun Microsystems. We will be using this language to build the backend service for Tether |
|   Postgresql   |     Open-source relational database management system.    |
|   SpringBoot   |     An open-source Java-based framework used to create a micro Service. This will be used to create and run our application.    |
|  Spring MVC	   |     Model-View-Controller. This is the architectural pattern that will be used to implement our system.  |
|   Spring Web	   |   Will be used to build our web application by using Spring MVC. This is one of the dependencies of our system.   |
|   API   |    Application Programming Interface. This will be used to interface the backend and the fronted of our application.     |
|   HTML   |     Hypertext Markup Language. This is the code that will be used to structure and design the web application and its content.    |
|   CSS   |     Cascading Style Sheets. Will be used to add styles and appearance to the web app.    |
|   JavaScript   |    An object-oriented computer programming language commonly used to create interactive effects within web browsers.Will be used in conjuction with HTML and CSS to make the web app.     |
|   VS Code   |     An integrated development environment (IDE) for Java. This is where our system will be created.    |


### 1.4 References
https://spring.io/guides

### 1.5 Document Overview
Section 1 is a general introduction to the document, intended for any readers. Section 2 is focused on the product and its features. This section is for customers and business stakeholders. Section 3 specifies the requirements and constraints for the product and development process. This section is intended for all stakeholders, especially the development team.

## 2. Product Overview
Tether is a web-based platform designed to allow users to connect through chatrooms, subscribe to those chatrooms, and make chatrooms themselves. Users can browse chatrooms, view friends activity, leave reviews based on the enjoyment and experience in the chatroom, and subscribe to gain special privileges to those chatrooms. Users are also able to track chat history, manage members, and edit their chatrooms. This app supports a flexible one role system giving the user freedom and flexibility to use the app as just a chatter, a chatroom host, or both.

### 2.1 Product Functions
Tether allows users to create and customize their chatrooms to display to other users. They can manage their chatrooms and users can subscribe to their chosen chatrooms. The owner of the chatroom is easily able to track activity and subscriptions. 

### 2.2 Product Constraints
At this point, the program will only run on a computer with Java jdk 21 installed. The full scope of the project is hopefully realized, however the team has a deadline of about 10 weeks, which could lead to feature cuts. The program would have a challenge scaling, as the current plan is to use a free version of a Postgresql database to store the information. 

### 2.3 User Characteristics
Our website application does not expect our users to have any prior knowledge of a computer, apart from using a web browser. If they have used a web communication app before, the application should be easy to understand. If the user has never used a web communication app, they should be expert level within several uses of the application.

### 2.4 Assumptions and Dependencies
We will be using Java, with our program being dependent on Spring & SpringBoot, and RestAPI to connect to external APIs and developed with VS Code. The application will also use an external nutrition API that will help customers learn the nutritional value of produce.

## 3. Requirements

### 3.1 Functional Requirements 
This section specifies the software product's requirements. Specify all of the software requirements to a level of detail sufficient to enable designers to design a software system to satisfy those requirements, and to enable testers to test that the software system satisfies those requirements.

The specific requirements should:
* Be uniquely identifiable.
* State the subject of the requirement (e.g., system, software, etc.) and what shall be done.
* Optionally state the conditions and constraints, if any.
* Describe every input (stimulus) into the software system, every output (response) from the software system, and all functions performed by the software system in response to an input or in support of an output.
* Be verifiable (e.g., the requirement realization can be proven to the customer's satisfaction)
* Conform to agreed upon syntax, keywords, and terms.

#### 3.1.1 User interfaces
For the user interface we shall implement the following:
Login/Registration Page, which will display Login, Register, while asking for username and password. Error messages will be displayed in clear text (e.g., “Invalid password” or “Username already exists”). 
Dashboard/Chatroom list, displays a list of available chatrooms the user can join or create. The buttons will be Create New Chatroom, Join Chatroom. Each chatroom shows the room name and number of participants. 
Chatroom window, center of the page will display messages and highlighted announcements. Left of the page will display list of chatrooms and participants. 

#### 3.1.2 Hardware interfaces
The hardware requirements for a project such as this one will run on a local development machine. 
Processor: Dual-core CPU (Intel i3 / AMD equivalent or higher).
Memory: 4 GB RAM.
Storage: 20 GB free disk space.
It will be able to connect to the schools wifi (eduroam) to handle ALL requests. Students shall be able to access the software through the usual devices i.e. Laptops, Computers, and mobile devices. As long as they can access a web browser they will be able to access the software.  

#### 3.1.3 Software interfaces
The software shall interact with these different software components, students will be able to access the software through web browsers like google Chrome, Firefox, etc. SQL 8 will be used to store user accounts, chats, and chatrooms. The backend framework will be built using Python, JSON will be used for requests and responses. In order to implement real-time messaging to the software, we will need to implement Django Channels. 

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
Our program should output the following performance: 
Search messages in a chatroom should be no more than  2 seconds for  more than 100 users.
Sending/receiving a message should take less than a second
File uploads should take no more than 10 seconds. 

#### 3.2.2 Security
In order to satisfy the security requirements for our program, we shall implement the following: 
Authentication and authorization i.e. user log in services (username and password)
Specified roles and permissions partitioned for each individual use case. 
Encryption of data by using javascript web API's like subtleCrypto. 
Passwords Stored are hashed.
Follow UNCGIT policies.

#### 3.2.3 Reliability
The software shall include the following to aid in reliability: Error handling, testing & quality assurance, checkpointing, rollback, code quality and maintainability. 

#### 3.2.4 Availability
The web interface shall comply with Web Content Accessibility Guidelines (WCAG 2.1, Level AA) to ensure accessibility for persons with disabilities.

#### 3.2.5 Compliance
The system shall comply with the organization's Information Security Policy for user authentication, access control, and logging.
The system will be in compliance with internal data archival and retention policies.

#### 3.2.6 Cost
Free, Premium shall be 9.99 monthly subscription.

#### 3.2.7 Deadline
End of Semester
