CREATE TABLE cliente_pet (
	cliente_id bigint NOT NULL,
	pet_id bigint NOT NULL,
	CONSTRAINT cliente_pet_un UNIQUE (cliente_id,pet_id)
);
