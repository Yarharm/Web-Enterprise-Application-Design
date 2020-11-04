# Message Board System

## Installation
1. Add __config.json__ file to the root of __WEB-INF__ folder and include the following (MySQL sample):
    - "JDBC_DRIVER":"com.mysql.cj.jdbc.Driver",
    - "DB_URL":"jdbc:mysql://localhost:3306/",
    - "DB_NAME":"preferred_db_name",
    - "DB_USER":"your_username",
    - "DB_PASSWORD":"your_password"
2. Run `PreloadUsers` driver to add default users to the DB.

## Database
- Use the following query to create the posts table.

```
CREATE TABLE `posts` (
 `userID` int(11) NOT NULL,
 `postID` int(11) NOT NULL AUTO_INCREMENT,
 `postTitle` varchar(96) NOT NULL,
 `timestamp` bigint(30) NOT NULL,
 `message` varchar(1024) NOT NULL,
 `attachment` blob DEFAULT NULL,
 PRIMARY KEY (`postID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4
```


- Add `Users` table to store the file with user information:
```
create table Users (UserFile BLOB);
```