package br.com.api.repository.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente_endereco")
@SequenceGenerator(name = "cliente_endereco_seq", sequenceName = "cliente_endereco_seq", allocationSize = 1)
public class ClienteEndereco {

    @Id
    @Column(name = "endereco_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_endereco_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "fg_ativo")
    private Boolean fgAtivo;

    private Integer numero;

    private String cep;
    private String tipo;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String complemento;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
