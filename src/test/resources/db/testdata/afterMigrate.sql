DELETE FROM cliente_pet;
DELETE FROM pet;
DELETE FROM cliente_endereco;
DELETE FROM cliente_contato;
DELETE FROM cliente;
DELETE FROM usuario;
DELETE FROM oauth_client_details;

INSERT INTO usuario VALUES (100, 'teste@teste.com', '$2y$12$xiBnzo1ZMSZgJi/A6b5ace6BXqqNaoppLE4bZUvDBE1kopY902.EG', true);
INSERT INTO usuario VALUES (200, 'teste2@teste.com', '$2y$12$xiBnzo1ZMSZgJi/A6b5ace6BXqqNaoppLE4bZUvDBE1kopY902.EG', true);
INSERT INTO usuario VALUES (300, 'teste3@teste.com', '$2y$12$xiBnzo1ZMSZgJi/A6b5ace6BXqqNaoppLE4bZUvDBE1kopY902.EG', true);
INSERT INTO usuario VALUES (400, 'teste4@teste.com', '$2y$12$xiBnzo1ZMSZgJi/A6b5ace6BXqqNaoppLE4bZUvDBE1kopY902.EG', true);

INSERT INTO cliente VALUES (100, 100, 'NOME TESTE', 'MASCULINO', now(), '848.223.370-06', true);
INSERT INTO cliente VALUES (200, 200, 'NOME TESTE 2', 'MASCULINO', now(), '165.849.220-06', true);
INSERT INTO cliente VALUES (300, 300, 'NOME TESTE 3', 'FEMININO', now(), '821.661.020-34', true);
INSERT INTO cliente VALUES (400, 400, 'NOME TESTE 4', 'FEMININO', now(), '657.591.000-92', true);

INSERT INTO pet VALUES (100, 100, 'teste-nome', 'teste-tipo', 'teste-raca', 'teste-cor', 'MEDIO', 'MACHO', now(), 20, true);
INSERT INTO pet VALUES (200, 200, 'teste-nome 2', 'teste-tipo 2', 'teste-raca 2', 'teste-cor 2', 'MEDIO 2', 'MACHO', now(), 20, true);
INSERT INTO pet VALUES (300, 300, 'teste-nome 3', 'teste-tipo 3', 'teste-raca 3', 'teste-cor 3', 'GRANDE 3', 'FEMEA', now(), 20, true);
INSERT INTO pet VALUES (400, 400, 'teste-nome 4', 'teste-tipo 4', 'teste-raca 4', 'teste-cor 4', 'PEQUENO 4', 'FEMEA', now(), 20, true);

INSERT INTO cliente_pet VALUES (100, 100);
INSERT INTO cliente_pet VALUES (200, 200);
INSERT INTO cliente_pet VALUES (300, 300);
INSERT INTO cliente_pet VALUES (400, 400);

INSERT INTO cliente_contato VALUES(100, 100, 'CELULAR', 912341234, 11, true);
INSERT INTO cliente_contato VALUES(200, 200, 'CELULAR', 912341234, 11, true);
INSERT INTO cliente_contato VALUES(300, 300, 'CELULAR', 912341234, 11, true);
INSERT INTO cliente_contato VALUES(400, 400, 'CELULAR', 912341234, 11, true);

INSERT INTO cliente_endereco VALUES(100, 100, 'Rua Teste', 'Residencial', '12345-000', 'SP', 'São Paulo', 'Vila Teste', 1, 'Complemento teste', true);
INSERT INTO cliente_endereco VALUES(200, 200, 'Rua Teste 2', 'Residencial 2', '12345-000', 'SP', 'São Paulo', 'Vila Teste 2', 1, 'Complemento teste 2', true);
INSERT INTO cliente_endereco VALUES(300, 300, 'Rua Teste 3', 'Residencial 3', '12345-000', 'SP', 'São Paulo', 'Vila Teste 3', 1, 'Complemento teste 3', true);
INSERT INTO cliente_endereco VALUES(400, 400, 'Rua Teste 4', 'Residencial 4', '12345-000', 'SP', 'São Paulo', 'Vila Teste 4', 1, 'Complemento teste 4', true);

insert into oauth_client_details values ('foo', null, '$2y$12$M7b32uoClK2qgzTzPMdw.usB84i3JzZ2mDbNl/7wPVnt.BxUucenC',
'READ,WRITE','password,refresh_token', null, null, 3000, 4200, null, null);
insert into oauth_client_details values ('apii', null, '$2y$12$/iDUcKjK8J2sADu10DMpLOC1lQeW1LmpNBAUE6.N5wyBKKu2ZLEiC',
'READ,WRITE','password,refresh_token', null, null, 3000, 4200, null, null);