package br.com.api.resource;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.dto.validacao.GruposValidacao;
import br.com.api.repository.entities.ClienteContato;
import br.com.api.repository.entities.ClienteEndereco;
import br.com.api.service.ClienteContatoService;
import br.com.api.service.ClienteEnderecoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
public class ClienteEnderecoResource {

    @Autowired
    private ClienteEnderecoService service;

    private final Logger logger = LoggerFactory.getLogger(ClienteEnderecoResource.class);

    @GetMapping
    public List<ClienteEnderecoDTO> listar() {

        logger.info("Listando enderecos ...");

        return  service.listar();
    }

    @GetMapping("/{enderecoId}")
    public ClienteEnderecoDTO buscarPorId(final @PathVariable Long enderecoId) {
        return service.buscarPorId(enderecoId);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ClienteEnderecoDTO> listarPorClienteId(final @PathVariable Long clienteId) {
        return service.buscarPorClienteId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteEnderecoDTO salvar(@RequestBody @Validated(GruposValidacao.ClienteEnderecoCadastro.class) final ClienteEnderecoDTO enderecoDTO) {
        return service.salvar(enderecoDTO);
    }

    @PutMapping
    public ClienteEnderecoDTO atualizar(@RequestBody final ClienteEnderecoDTO enderecoDTO) {
        return service.atualizar(enderecoDTO);
    }

    @DeleteMapping("{enderecoId}")
    public void excluir(@PathVariable final Long enderecoId) {
        service.excluir(enderecoId);
    }

}
