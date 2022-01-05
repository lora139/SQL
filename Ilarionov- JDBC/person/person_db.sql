CREATE DATABASE IF NOT EXISTS Prsn CHARACTER SET 'utf8mb4';
-- Person's base Info Only
Use Prsn;

CREATE TABLE IF NOT EXISTS Person( -- Main Table
id INTEGER PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(12) NOT NULL,
mid_name VARCHAR(12) DEFAULT '', -- unique names
last_name VARCHAR(12) NOT NULL,
create_at DATETIME DEFAULT current_timestamp,
CONSTRAINT unique_names UNIQUE (first_name, mid_name, last_name)
);
-- insert as a single transcription 
INSERT INTO Person (first_name, last_name) VALUE ('Ivan', 'Stoqnov'), ('Stoqn', 'Ivanov'), ('Lora', 'Dimitrova');
SELECT * FROM Person;

CREATE TABLE IF NOT EXISTS Email( -- Many phones related to a Specific Person 
-- secondary, auhiliary table: Add info to main table
id INTEGER PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(20) NOT NULL UNIQUE,
id_person INTEGER NOT NULL, -- N:1 RelationShip
CONSTRAINT e_mail FOREIGN KEY (id_person)
REFERENCES Person(id)
ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Email (email, id_person) VALUES ('lolo@gmail.com', 2),('did@abv.bg', 1), ('iva@mniob', 3);
SELECT * FROM Email;