CREATE TABLE  strap (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
status VARCHAR(255),
state VARCHAR(255),
suspect VARCHAR(255),
minvalueref VARCHAR (255),
maxvalueref VARCHAR(255),
ipadress VARCHAR(255),
price double,
minsysto VARCHAR(32),
maxsysto VARCHAR(32),
maxdiasto VARCHAR(32),
minglyc VARCHAR(32),
maxglyc VARCHAR(32),
minsteps VARCHAR(32),
person_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_strap_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);
