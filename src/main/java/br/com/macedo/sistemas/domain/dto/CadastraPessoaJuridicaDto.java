package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CadastraPessoaJuridicaDto implements Serializable {
    private final LocalDate dataAbertura;
    private final String nomeFantasia;
    private final String nomeEmpresarial;
    private final String cnpj;
    private final EnderecoEntityDto endereco;
    private final PorteEmpresarialEntityDto porteEmpresarial;
}
