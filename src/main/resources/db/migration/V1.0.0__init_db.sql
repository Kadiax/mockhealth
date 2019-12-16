CREATE TABLE residence (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
adress VARCHAR(255),
phone VARCHAR(35),
email VARCHAR(55)
);



CREATE TABLE person (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
firstname VARCHAR (255),
lastname VARCHAR (255),
birthdate VARCHAR(20),
email VARCHAR(255),
phone VARCHAR(25),
averageincome VARCHAR(255),
handicap VARCHAR(255),
login VARCHAR (255),
password VARCHAR (255),
deseas VARCHAR(255),
ismobile VARCHAR(255),
userevaluation INT,
userrole VARCHAR (255),
residence_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_person_residence_id FOREIGN KEY (residence_id) REFERENCES residence(id)
);