# Message Board System

## Installation
1. Add __config.json__ file to the root of __MessageBoardSystem__ module and include the following (MySQL sample):
    - "JDBC_DRIVER":"com.mysql.cj.jdbc.Driver",
    - "DB_URL":"jdbc:mysql://localhost:3306/",
    - "DB_NAME":"preferred_db_name",
    - "DB_USER":"your_username",
    - "DB_PASSWORD":"your_password"
2. Run `PreloadUsers` driver to add default users to the DB.

## SQL
- Add `Users` table to store the file with user information:
`create table Users (UserFile BLOB);`