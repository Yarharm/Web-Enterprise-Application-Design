# Message Board System

## Database
- Use the following query to create the posts table.
```
CREATE TABLE `post` (
 `postID` int(11) NOT NULL AUTO_INCREMENT,
 `userID` int(11) NOT NULL,
 `username` varchar(96) NOT NULL,
 `postTitle` varchar(96) NOT NULL,
 `timestamp` bigint(30) NOT NULL,
 `dateString` varchar(100) NOT NULL,
 `lastModifiedTimestamp` bigint(30) NOT NULL,
 `message` varchar(1024) NOT NULL,
 PRIMARY KEY (`postID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4
```

- Add `AttachmentInfo` table to store information about attachments
```
CREATE TABLE attachment (
postID int NOT NULL,
fileName varchar(255) NOT NULL,
fileSize bigint(30) NOT NULL,
mediaType varchar(255) NOT NULL,
attachment longblob NOT NULL,
PRIMARY KEY(postID),
FOREIGN KEY(postID) REFERENCES post(postID) ON DELETE CASCADE);
```

- Add `hastag` table to store information about hashtag
```
CREATE TABLE hashtag (
hashtag varchar(255) NOT NULL,
postID int NOT NULL,
PRIMARY KEY(hashtag,postID),
FOREIGN KEY(postID) REFERENCES post(postID) ON DELETE CASCADE);
```

## Installation
1. Add __config.json__ file to the root of __WEB-INF__ folder and include the following (MySQL sample):
    - "JDBC_DRIVER":"com.mysql.cj.jdbc.Driver",
    - "DB_URL":"jdbc:mysql://localhost:3306/",
    - "DB_NAME":"preferred_db_name",
    - "DB_USER":"your_username",
    - "DB_PASSWORD":"your_password",
    - "PAGINATION_SIZE":"10"
2. __users.json__ file should be available in __WEB-INF__ folder. If it does not exist then please run `PreloadUsers` script to generate a file and place it in the root of the __WEB-INF__ folder.