package br.com.api.resource;

import br.com.api.dto.PetDTO;
import br.com.api.dto.validacao.GruposValidacao;
import br.com.api.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetResource {

    @Autowired
    private PetService service;

    private final Logger logger = LoggerFactory.getLogger(PetResource.class);

    @GetMapping
    public List<PetDTO> listar() {
        final List<PetDTO> listar = service.listar();
        return listar;
    }

    @GetMapping("/{petId}")
    public PetDTO buscarPorId(final @PathVariable Long petId) {
        final PetDTO petDTO = service.buscarPorId(petId);
        return petDTO;
    }

    @GetMapping("/cliente/{clienteId}")
    public List<PetDTO> listarPorClienteId(final @PathVariable Long clienteId) {
        return service.buscarPorCliente(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetDTO salvar(@RequestBody @Validated(GruposValidacao.PetCadastro.class) final PetDTO petDTO) {
        return service.salvar(petDTO);
    }

    @PutMapping
    public PetDTO atualizar(@RequestBody final PetDTO petDTO) {
        return service.atualizar(petDTO);
    }

    @DeleteMapping("/{petId}")
    public void deletar(@PathVariable final Long petId) {
        service.excluir(petId);
    }

}
