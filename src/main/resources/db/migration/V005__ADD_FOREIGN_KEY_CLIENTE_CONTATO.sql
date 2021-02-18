ALTER TABLE cliente_contato ADD CONSTRAINT cliente_contato_cliente_fk FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id);
