create table sigfap.edital (
	id SERIAL PRIMARY KEY,
	numero VARCHAR(64) UNIQUE NOT NULL,
	titulo VARCHAR(256),
	descricao TEXT
);