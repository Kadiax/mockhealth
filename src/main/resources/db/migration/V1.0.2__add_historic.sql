CREATE TABLE healthhistoric (
id SERIAL PRIMARY KEY,
optlock TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
hearthrate VARCHAR(255),
systolic VARCHAR(255),
diastolic VARCHAR(255),
sugarlevel VARCHAR(255),
stepcounter VARCHAR(255),
strap_id BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_healthhistoric_strap_id FOREIGN KEY (strap_id) REFERENCES strap(id)
);
