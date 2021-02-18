ALTER TABLE cliente_endereco ADD CONSTRAINT cliente_endereco_cliente_fk FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id);
