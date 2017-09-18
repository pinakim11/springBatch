DROP TABLE CAR;

CREATE TABLE CAR(

    ID INT NOT NULL identity(1, 1) primary key,
	licensePlate [varchar](20),
	manufacturer VARCHAR(20),
	manufactureDate VARCHAR(120),
	age INT NOT NULL DEFAULT 0,
	version INT NOT NULL DEFAULT 0

)


