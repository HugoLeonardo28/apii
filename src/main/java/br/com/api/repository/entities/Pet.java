package br.com.api.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", allocationSize = 1)
public class Pet {

    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    private Long id;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "fg_ativo")
    private Boolean fgAtivo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Double peso;

    private String cor;
    private String nome;
    private String tipo;
    private String raca;
    private String sexo;
    private String porte;

}

