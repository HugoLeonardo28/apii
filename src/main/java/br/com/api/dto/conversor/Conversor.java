package br.com.api.dto.conversor;

public interface Conversor <D, E>{

    public D converterParaDTO(E entidade);
    public E converterParaEntidade(D dto);
}
