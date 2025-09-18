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
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
Describe the purpose of the SRS and its intended audience.

### 1.2 Product Scope
Identify the product whose software requirements are specified in this document, including the revision or release number. Explain what the product that is covered by this SRS will do, particularly if this SRS describes only part of the system or a single subsystem. 
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations                                                                                                                                                                          |

### 1.4 References
List any other documents or Web addresses to which this SRS refers. These may include user interface style guides, contracts, standards, system requirements specifications, use case documents, or a vision and scope document. Provide enough information so that the reader could access a copy of each reference, including title, author, version number, date, and source or location.

### 1.5 Document Overview
Describe what the rest of the document contains and how it is organized.

## 2. Product Overview
This section should describe the general factors that affect the product and its requirements. This section does not state specific requirements. Instead, it provides a background for those requirements, which are defined in detail in Section 3, and makes them easier to understand.

### 2.1 Product Functions
Summarize the major functions the product must perform or must let the user perform. Details will be provided in Section 3, so only a high level summary (such as a bullet list) is needed here. Organize the functions to make them understandable to any reader of the SRS. A picture of the major groups of related requirements and how they relate, such as a top level data flow diagram or object class diagram, is often effective.

### 2.2 Product Constraints
This subsection should provide a general description of any other items that will limit the developer’s options. These may include:  

* Interfaces to users, other applications or hardware.  
* Quality of service constraints.  
* Standards compliance.  
* Constraints around design or implementation.
  
### 2.3 User Characteristics
Identify the various user classes that you anticipate will use this product. User classes may be differentiated based on frequency of use, subset of product functions used, technical expertise, security or privilege levels, educational level, or experience. Describe the pertinent characteristics of each user class. Certain requirements may pertain only to certain user classes. Distinguish the most important user classes for this product from those who are less important to satisfy.

### 2.4 Assumptions and Dependencies
List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints. The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).

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
