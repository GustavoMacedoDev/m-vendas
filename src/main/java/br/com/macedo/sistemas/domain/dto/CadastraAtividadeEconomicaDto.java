package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.ObservacaoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CadastraAtividadeEconomicaDto implements Serializable {
    private final String idCnae;
    private final String descricaoCnae;
    private final GrupoCnaeEntityDto grupoCnae;
    private List<String> observacoes;
}
