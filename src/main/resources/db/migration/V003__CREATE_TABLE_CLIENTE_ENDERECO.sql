CREATE TABLE cliente_endereco (
	endereco_id bigint NOT NULL,
	cliente_id bigint NOT NULL,
	logradouro varchar(200) NOT NULL,
	tipo varchar(20) NOT NULL,
	cep varchar(10) NOT NULL,
	estado varchar(20) NOT NULL,
	cidade varchar(100) NOT NULL,
	bairro varchar(100) NOT NULL,
	numero int NOT NULL,
	complemento varchar(100) NOT NULL,
	fg_ativo bool NOT NULL,
	CONSTRAINT endereco_pk PRIMARY KEY (endereco_id)
);