package br.com.api.resource;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.validacao.GruposValidacao;
import br.com.api.dto.validacao.GruposValidacao.ClienteContatoCadastro;
import br.com.api.service.ClienteContatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contato")
public class ClienteContatoResource {

    @Autowired
    private ClienteContatoService service;

    private final Logger logger = LoggerFactory.getLogger(ClienteContatoResource.class);

    @GetMapping
    public List<ClienteContatoDTO> listar() {

        logger.info("Listando contatos ...");

        return service.listar();
    }

    @GetMapping("/{contatoId}")
    public ClienteContatoDTO buscarPorId(final @PathVariable Long contatoId) {
        return service.buscarPorId(contatoId);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ClienteContatoDTO> buscarPorClienteId(final @PathVariable Long clienteId) {
        return service.listarPorClienteId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteContatoDTO salvar(@RequestBody @Validated(ClienteContatoCadastro.class) final ClienteContatoDTO contatoDTO) {
        return  service.salvar(contatoDTO);
    }

    @PutMapping
    public ClienteContatoDTO atualizar(@RequestBody final ClienteContatoDTO contatoDTO) {
       return service.atualizar(contatoDTO);
    }

    @DeleteMapping("{contatoId}")
    public void excluir(@PathVariable final Long contatoId) {
        service.excluir(contatoId);
    }

}
