package br.com.api.repository.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente_contato")
@SequenceGenerator(name = "cliente_contato_seq", sequenceName = "cliente_contato_seq", allocationSize = 1)
public class ClienteContato {

    @Id
    @Column(name = "contato_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_contato_seq")
    private Long id;

    @Column(name = "tipo_contato")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "fg_ativo")
    private Boolean fgAtivo;

    private Integer ddd;
    private Integer numero;
}
