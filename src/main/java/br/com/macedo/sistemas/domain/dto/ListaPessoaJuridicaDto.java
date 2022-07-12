package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.EnderecoEntity;
import br.com.macedo.sistemas.domain.aggregate.PessoaJuridicaEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ListaPessoaJuridicaDto implements Serializable {
    private final LocalDate dataAbertura;
    private final String nomeFantasia;
    private final String nomeEmpresarial;
    private final String cnpj;
    private final EnderecoEntity endereco;

    public ListaPessoaJuridicaDto(PessoaJuridicaEntity pjEntity) {
        this.dataAbertura = pjEntity.getDataAbertura();
        this.nomeFantasia = pjEntity.getNomeFantasia();
        this.nomeEmpresarial = pjEntity.getNomeEmpresarial();
        this.cnpj = pjEntity.getCnpj();
        this.endereco = pjEntity.getEndereco();
    }
}
