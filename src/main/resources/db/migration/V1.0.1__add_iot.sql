CREATE TABLE  light (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
startdate TIMESTAMP(3),
minvalueref VARCHAR (255),
ipadress VARCHAR(255),
maxvalueref VARCHAR(255),
activityduration TIMESTAMP(3),
color VARCHAR(6),
percentage VARCHAR(5),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_iot_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE  shutter (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
startdate TIMESTAMP(3),
ipadress VARCHAR(255),
minvalueref VARCHAR (255),
maxvalueref VARCHAR(255),
activityduration TIMESTAMP(3),
percentage VARCHAR(5),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_shutter_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE  heater (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
ipadress VARCHAR(255),
startdate TIMESTAMP(3),
minvalueref VARCHAR (255),
maxvalueref VARCHAR(255),
activityduration TIMESTAMP(3),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_heater_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE  clock (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
startdate TIMESTAMP(3),
ipadress VARCHAR(255),
minvalueref VARCHAR (255),
maxvalueref VARCHAR(255),
activityduration TIMESTAMP(3),
time TIMESTAMP(3),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_clock_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE  strap (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
startdate TIMESTAMP(3),
minvalueref VARCHAR (255),
maxvalueref VARCHAR(255),
ipadress VARCHAR(255),
activityduration TIMESTAMP(3),
minsysto VARCHAR(32),
maxsysto VARCHAR(32),
maxdiasto VARCHAR(32),
minglyc VARCHAR(32),
maxglyc VARCHAR(32),
minsteps VARCHAR(32),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_strap_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);
