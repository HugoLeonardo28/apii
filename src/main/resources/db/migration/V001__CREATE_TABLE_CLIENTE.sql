CREATE TABLE cliente (
	cliente_id bigint NOT NULL,
	usuario_id bigint NOT NULL,
	nome varchar(200) NOT NULL,
	sexo varchar(10) NOT NULL,
	data_nascimento date NOT NULL,
	cpf varchar(15) NOT NULL,
	fg_ativo bool NOT NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (cliente_id)
);