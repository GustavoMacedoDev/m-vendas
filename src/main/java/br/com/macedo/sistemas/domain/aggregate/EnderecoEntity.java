package br.com.macedo.sistemas.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class EnderecoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long idEndereco;

    @ManyToOne
    private LogradouroEntity logradouroEntity;

    private String numero;

    private String cep;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private BairroEntity bairroEntity;


}
