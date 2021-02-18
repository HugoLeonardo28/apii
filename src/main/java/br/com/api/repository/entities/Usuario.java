package br.com.api.repository.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;

    @Column(name = "fg_ativo")
    private Boolean fgAtivo;

    private String email;
    private String senha;
}
