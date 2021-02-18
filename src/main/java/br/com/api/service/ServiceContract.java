package br.com.api.service;

import java.util.List;

public interface ServiceContract <T, D>{

    public List<D> listar();
    public D buscarPorId(final Long id);
    public D salvar (D dto);
    public void excluir(Long id);
    public D atualizar(D dto);
}
