CREATE TABLE alerthealth (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
message VARCHAR(255),
startdate TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
criticity VARCHAR(255),
strap_id VARCHAR(255)
);
