package br.com.macedo.sistemas.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@Table(name = "pesssoa_juridica", uniqueConstraints = {@UniqueConstraint(columnNames = "cnpj")})
@Getter
@Setter
public class PessoaJuridicaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa_juridica", nullable = false)
    private Long idPessoaJuridica;

    private LocalDate dataAbertura;

    private String nomeFantasia;

    private String nomeEmpresarial;

    @CNPJ
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;

    @ManyToOne
    @JoinColumn(name = "id_porte_empresarial")
    private PorteEmpresarialEntity porteEmpresarial;


}
