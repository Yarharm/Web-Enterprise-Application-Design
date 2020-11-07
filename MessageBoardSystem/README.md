# Message Board System

## Database
- Use the following query to create the posts table.
```
CREATE TABLE `posts` (
 `postID` int(11) NOT NULL AUTO_INCREMENT,
 `userID` int(11) NOT NULL,
 `postTitle` varchar(96) NOT NULL,
 `timestamp` bigint(30) NOT NULL,
 `message` varchar(1024) NOT NULL,
 PRIMARY KEY (`postID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4
```

- Add `AttachmentInfo` table to store information about attachments
```
CREATE TABLE Attachments (
postID int NOT NULL,
fileName varchar(255) NOT NULL,
fileSize bigint(30) NOT NULL,
mediaType varchar(255) NOT NULL,
attachment longblob NOT NULL,
PRIMARY KEY(postID));
```

- Add `Users` table to store the file with user information:
```
create table Users (UserFile BLOB);
```

## Installation
1. Add __config.json__ file to the root of __WEB-INF__ folder and include the following (MySQL sample):
    - "JDBC_DRIVER":"com.mysql.cj.jdbc.Driver",
    - "DB_URL":"jdbc:mysql://localhost:3306/",
    - "DB_NAME":"preferred_db_name",
    - "DB_USER":"your_username",
    - "DB_PASSWORD":"your_password"
2. Run `PreloadUsers` driver to add default users to the DB.