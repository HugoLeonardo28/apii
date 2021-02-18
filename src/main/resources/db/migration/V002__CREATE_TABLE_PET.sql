CREATE TABLE pet (
	pet_id bigint NOT NULL,
	cliente_id bigint NOT NULL,
	nome varchar(200) NOT NULL,
	tipo varchar(50) NOT NULL,
	raca varchar(50) NOT NULL,
	cor varchar(50) NOT NULL,
	porte varchar(10) NOT NULL,
	sexo varchar(10) NOT NULL,
	data_nascimento date NOT NULL,
	peso float4 NOT NULL,
	fg_ativo bool NOT NULL,
	CONSTRAINT pet_pk PRIMARY KEY (pet_id)
);
