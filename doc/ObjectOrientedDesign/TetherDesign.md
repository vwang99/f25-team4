# Tether Hub - Software Design 

Version 1  
Prepared by Jesus Jut Lopez\
LocalHarvest Hub\
Oct 21, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Administrator](#221-actor-farmer)
    * 2.2.2 [Actor: Member](#222-actor-customer) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  Jesus J Lopez  |10/19     | Initial Design      |    1      |
|      |         |                     |           |
|      |         |                     |           |

## 1. Product Overview
Tether is a simple, easy to use chatroom service that allows communication between different users within different chatrooms. These chatrooms can cover a range of topics that users may be interested in. The contents of the website is user-generated, meaning that users are allowed to create, moderate, and post within these chatrooms. 

## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model](https://github.com/vwang99/f25-team4/blob/jlopez-milestone4/doc/ObjectOrientedDesign/classDiagram.png)

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: User
##### 2.2.1.1 Sign Up
A user can sign up to create their profile with their name, email, password, and phone number. Emails must be unique.
##### 2.2.1.2 Log In
A user shall be able to log in using their registered email and password. After logging in, the user is redirected to the homepage, where they can view a list of joined and available chatrooms.
##### 2.2.1.3 Join Chatroom
A logged-in user can browse chatrooms. When joining, the system adds the user to that chatroom’s member list.
The user can then participate in conversations and view the chatroom history.
##### 2.2.1.4 Leave Chatroom
A user may leave a chatroom they joined. Once they leave, they will no longer receive messages or notifications from that chatroom.
##### 2.2.1.4 Send Message
A user can send a message in any chatroom they are a member of. The system stores the message, timestamps it, and displays it instantly to all chatroom members.

##### 2.2.1.6 Edit or Delete Message
A user may edit or delete messages they previously sent. When a message is edited, all users in the chatroom see the updated version marked as “edited.” Deleted messages are removed from the chatroom feed.

##### 2.2.1.8 Write Review
A user can write a review for a chatroom they have joined. They can rate the chatroom (1–5 stars) and leave a short comment. Once submitted, the review is visible on the chatroom’s page.

##### 2.2.1.9 Edit or Delete Review
A user can edit or delete their own reviews. Edits allow users to change the rating or comment; deletions permanently remove the review.

##### 2.2.1.10 Create Chatroom
A user can create a new chatroom by providing a name, description, and privacy setting (public or private).
The system assigns them as the chatroom owner with administrative privileges.

#### 2.2.2 Actor: Administrator
##### 2.2.2.1 Sign Up
A Administrator can sign up to create their profile with their name, email, password, and address. Emails must be unique.
##### 2.2.2.2 Log In
A Administrator shall be able to sign in using their registred email and password. After logging in, the customer shall be directed their dashboard where they see an overview of the chatrooms they can moderate.
##### 2.2.2.3 View Flagged Content
Admins and moderators can review messages or chatrooms that have been flagged by users.
They can decide to remove the content or warn the sender.
##### 2.2.2.4 Remove Messages
Moderators can delete inappropriate messages from the chatroom.
The deleted message is replaced with a system notice: “Message removed by moderator.”
##### 2.2.2.5 Assign Roles
Admins can assign roles (Admin, Moderator, Member) to users within a chatroom.
Each role determines the user’s permissions — such as sending messages, kicking members, or managing settings.
##### 2.2.2.6 Post Announcement
A moderator or admin can post announcements in a chatroom. These messages are pinned and visible to all members.
##### 2.2.2.7 Manage Members
The chatroom owner or assigned moderators can add, remove, or mute members. Member management is restricted to users with the proper role permissions.

## 3. UML Class Diagram
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/class-diagram.png)
## 4. Database Schema
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/schema.png)
