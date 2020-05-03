CREATE DATABASE testDB;

USE JOB4;

CREATE TABLE User (
  id SERIAL PRIMARY KEY,
  roleId INT REFERENCES Role(id),
  name VARCHAR (30) NOT NULL,
  Email VARCHAR (30) UNIQUE CHECK(Email != '')
);

CREATE TABLE Role (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30),
  description TEXT,
  CHECK((name != '') AND (description != ''))
);

CREATE TABLE Rules (
  id SERIAL PRIMARY KEY,
  name VARCHAR (30) NOT NULL,
  description TEXT NOT NULL
);

CREATE TABLE roleAndRules (
  roleId INT REFERENCES Role(id),
  ruleID INT REFERENCES Rules(id)
);

CREATE TABLE Category (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
description TEXT NOT NULL
);

CREATE TABLE State (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
description TEXT NOT NULL
);

CREATE TABLE Items (
  id SERIAL PRIMARY KEY,
  userId INT REFERENCES User(id),
  categoryId INT REFERENCES Category(id),
  stateId INT REFERENCES State(id),
  name VARCHAR(30) NOT NULL,
  description TEXT NOT NULL
);

CREATE TABLE Attachs(
  id SERIAL PRIMARY KEY,
  itemId INT REFERENCES Item(id),
  name VARCHAR(30) NIT NULL,
fileJson TEXT NOT NULL
);

INSERT INTO Role(name , description) VALUES ('admin', 'project leader');
INSERT INTO ROle(name, description) VALUES ('user', 'project user');

INSERT INTO Rules(name, description) VALUES ('block_user', 'can block', 'user');
INSERT INTO Rules(name, description) VALUES ('add_user', 'can add user');
INSERT INTO Rules(name, description) VALUES ('create_item', 'can create some item');

INSERT INTO roleAndRules (roleId, ruleId) VALUES (1, 1);
INSERT INTO roleAndRules (roleId, ruleId) VALUES (1, 2);
INSERT INTO roleAndRules (roleId, ruleId) VALUES (1, 3);
INSERT INTO roleAndRules (roleId, ruleId) VALUES (2, 3);

INSERT INTO Category(name, description) VALUES ('check_solution', 'check solution task');
INSERT INTO Category(name, description) VALUES ('carry_out_an_audit');

INSERT INTO State (name,  description) VALUES ('new', 'new item');
INSERT INTO State (name,  description) VALUES ('in_work', 'this item in work');
INSERT INTO State (name,  description) VALUES ('close', 'this item is closed');

INSERT INTO User (roleId, name, Email) VALUES (1, 'Ivan_Telegin', 'ivan@gmail.com');
INSERT INTO User (roleId, name, Email) VALUES (2, 'Alexandr Petrov', 'sash@gmail.com');

INSERT INTO Items(userId, categoryId, stateId, name, description) VALUES (2, 1, 1, 'SQL', 'check new SQL task');
INSERT INTO Items(userId, categoryId, stateId, name, description) VALUES (2, 1, 1, 'JDBC', 'check new JDBC task');

INSERT INTO Comments(itemId, name, description) VALUES (1, 'checked_task', 'checked task and find some errors. I attach some screenShot');

INSERT INTO Attachs (itemId, name, fileJson) VALUES (1, 'screenShot', 'json object');

