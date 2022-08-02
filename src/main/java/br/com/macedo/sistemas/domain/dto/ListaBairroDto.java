package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.BairroEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ListaBairroDto implements Serializable {
    private List<String> bairros;

}
