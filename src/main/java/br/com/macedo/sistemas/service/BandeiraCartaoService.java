package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.BandeiraCartaoEntity;
import br.com.macedo.sistemas.domain.dto.ListagemBandeiraCartaoDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BandeiraCartaoService {


    public List<ListagemBandeiraCartaoDto> listaBandeiras() {
        List<BandeiraCartaoEntity> listaBandeiras = BandeiraCartaoEntity.listAll();

        List<ListagemBandeiraCartaoDto> bandeiras = new ArrayList<>();
        for(BandeiraCartaoEntity bandeiraCartaoEntity : listaBandeiras) {
            ListagemBandeiraCartaoDto bandeiraCartaoDto =  new ListagemBandeiraCartaoDto(bandeiraCartaoEntity);

            bandeiras.add(bandeiraCartaoDto);
        }


        return bandeiras;
    }
}
