CREATE TABLE cliente_contato (
	contato_id bigint NOT NULL,
	cliente_id bigint NOT NULL,
	tipo_contato varchar NOT NULL,
	numero int NOT NULL,
	ddd int NOT NULL,
	fg_ativo bool NOT NULL,
	CONSTRAINT cliente_contato_pk PRIMARY KEY (contato_id)
);