# Message Board System
An online discussion site where people can hold conversations in the form of posted messages.

## Team Description 👬
- Yaroslav, 40039584
- John, 40068376
- Ismail, 40025457

## Software Requirements 📜
- Java
- Maven
- Tomcat
- MySQL database. _Adjust POM dependencies for a different database_ ❗ 

## Installation 📦
1. Clone the repository
2. Add __config.json__ file to the root of __WEB-INF__ folder and include the following (MySQL sample):
    - "JDBC_DRIVER":"com.mysql.cj.jdbc.Driver",
    - "DB_URL":"jdbc:mysql://localhost:3306/",
    - "DB_NAME":"preferred_db_name",
    - "DB_USER":"your_username",
    - "DB_PASSWORD":"your_password",
    - "PAGINATION_SIZE":"10"
3. Run `PreloadUsers` script to generate predefined users and add the file to the root of __WEB-INF__ folder.
4. Populate the database with the following tables:
    - ```
      CREATE TABLE `post` (
       `postID` int(11) NOT NULL AUTO_INCREMENT,
       `userID` int(11) NOT NULL,
       `username` varchar(96) NOT NULL,
       `postTitle` varchar(96) NOT NULL,
       `timestamp` bigint(30) NOT NULL,
       `dateString` varchar(100) NOT NULL,
       `lastModifiedTimestamp` bigint(30) NOT NULL,
       `message` varchar(1024) NOT NULL,
       `postGroup` varchar(1024) NOT NULL,
       PRIMARY KEY (`postID`)
      ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4
      ```
    - ```
      CREATE TABLE attachment (
      postID int NOT NULL,
      fileName varchar(255) NOT NULL,
      fileSize bigint(30) NOT NULL,
      mediaType varchar(255) NOT NULL,
      attachment longblob NOT NULL,
      PRIMARY KEY(postID),
      FOREIGN KEY(postID) REFERENCES post(postID) ON DELETE CASCADE);
      ```
    - ```
      CREATE TABLE hashtag (
      hashtag varchar(255) NOT NULL,
      postID int NOT NULL,
      PRIMARY KEY(hashtag,postID),
      FOREIGN KEY(postID) REFERENCES post(postID) ON DELETE CASCADE);
      ```

## High level design 🚧

### Class diagram 📕
![alt text](https://presentation-387.s3.amazonaws.com/Class+diagram.png)

### Use Case diagram 📗
![alt text](https://presentation-387.s3.amazonaws.com/Use+Case+diagram.png)

### Entity relationship diagram 📘
![alt text](https://presentation-387.s3.amazonaws.com/ER+diagram.png)

### Create post sequence diagram 📔
![alt text](https://presentation-387.s3.amazonaws.com/Create+post+diagram.png)

### File download sequence diagram 📙 
![alt text](https://presentation-387.s3.amazonaws.com/Download+diagram.png)

## Layers Description 📚

### Presentation layer 📺
We handle authentication via Sessions by injecting valid user ids into Session Storage.
Only authenticated users has access to the UI and can execute functionality outlined in __Use Case diagram__.
Unauthenticated users have access to the following functionalities only:
- Login
- Registration (❗Not finished yet❗)
Presentation layer is aware only of the business layer and request information from it.

### Business Layer 🧠
Business layer handles all backend process communicated by the servlet. 
It is the only link that connects persistence and a presentation and responsible for the data transfer between the two.
It manipulates the following list of models:
- User
- Post
- Attachment
- Hashtag

### Persistence Layer 📝
User data is stored in a Readonly access file that is JSON formatted.
All posts are stored inside an SQL database.
Support for all attatchments is available, and they are stored as a Blob in a database.