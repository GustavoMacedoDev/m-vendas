package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.BairroEntity;
import br.com.macedo.sistemas.domain.dto.ListaBairroDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BairroService {

    public ListaBairroDto listaBairro() {
        List<BairroEntity> bairros = BairroEntity.listAll();
        List<String> listaBairroResponse = new ArrayList<>();
        for(BairroEntity bairroEntity: bairros){

            listaBairroResponse.add(bairroEntity.getNomeBairro());
        }

        ListaBairroDto listaBairroDto = new ListaBairroDto(listaBairroResponse);

        return listaBairroDto;
    }
}
