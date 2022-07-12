package br.com.macedo.sistemas.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CadastraPessoaJuridicaDto implements Serializable {
    private final LocalDate dataAbertura;
    private final String nomeFantasia;
    private final String nomeEmpresarial;
    @CNPJ(message = "Cnpj inválido")
    private final String cnpj;
    private final EnderecoEntityDto endereco;
}
