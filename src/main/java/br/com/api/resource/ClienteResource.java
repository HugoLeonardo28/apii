package br.com.api.resource;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.validacao.GruposValidacao;
import br.com.api.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    private final Logger logger = LoggerFactory.getLogger(ClienteResource.class);

    @GetMapping
    public List<ClienteDTO> listar() {

        logger.info("Listando clientes ...");

        return service.listar();
    }

    @GetMapping("/{clienteId}")
    public ClienteDTO buscarPorId(final @PathVariable Long clienteId) {

        return service.buscarPorId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvar(@RequestBody @Validated(GruposValidacao.ClienteCadastro.class) final ClienteDTO clienteDTO) {
        return service.salvar(clienteDTO);
    }

    @PutMapping
    public ClienteDTO atualizar(@RequestBody final ClienteDTO clienteDTO) {
        return service.atualizar(clienteDTO);
    }

    @DeleteMapping("/{clienteId}")
    public void deletar(@PathVariable final Long clienteId) {
        service.excluir(clienteId);
    }
}
