package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.BandeiraCartaoEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BandeiraCartaoService {


    public List<String> listaBandeiras() {
        List<BandeiraCartaoEntity> listaBandeiras = BandeiraCartaoEntity.listAll();

        List<String> bandeiras = new ArrayList<>();
        for(BandeiraCartaoEntity bandeiraCartaoEntity : listaBandeiras) {
            bandeiras.add(bandeiraCartaoEntity.getNomeBandeiraCartao());

        }


        return bandeiras;
    }
}
