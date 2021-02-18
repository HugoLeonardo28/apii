CREATE TABLE usuario (
	usuario_id bigint NOT NULL,
	email varchar(200) NOT NULL,
	senha varchar(200) NOT NULL,
	fg_ativo bool NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (usuario_id)
);
