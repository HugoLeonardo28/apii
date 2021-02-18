package br.com.api.repository.entities;

import lombok.*;
import org.springframework.stereotype.Component;
import sun.nio.cs.ext.MacArabic;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Table
@Entity
@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
public class Cliente {

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente() {
    }

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente")
    private List<ClienteEndereco> enderecos;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente")
    private List<ClienteContato> contatos;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente")
    private List<Pet> pets;

    @Column(name = "fg_ativo")
    private Boolean fgAtivo;

    private String cpf;
    private String nome;
    private String sexo;



}
