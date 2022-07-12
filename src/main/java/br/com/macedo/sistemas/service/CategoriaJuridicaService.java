package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import br.com.macedo.sistemas.domain.dto.CadastraCategoriaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListagemCategoriaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.CategoriaJuridicaRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoriaJuridicaService {

    private final static Logger LOGGER = Logger.getLogger(TipoEnderecoService.class);

    @Inject
    CategoriaJuridicaRepository categoriaJuridicaRepository;

    public List<ListagemCategoriaJuridicaDto> listaTodasCategorias() {
        List<CategoriaJuridicaEntity> listaCategorias = CategoriaJuridicaEntity.listAll();
        List<ListagemCategoriaJuridicaDto> listaCategoriasResponse = new ArrayList<>();

        for(CategoriaJuridicaEntity categoriaJuridica : listaCategorias) {
            ListagemCategoriaJuridicaDto listagemCategoriaJuridicaDto = new ListagemCategoriaJuridicaDto(categoriaJuridica);

            listaCategoriasResponse.add(listagemCategoriaJuridicaDto);
        }

        return listaCategoriasResponse;
    }


    @Transactional
    public MensagemResposta cadastraCategoriaJuridica(CadastraCategoriaJuridicaDto cadastraCategoriaJuridicaDto) {
        buscaCategoriaJuridicaPeloNome(cadastraCategoriaJuridicaDto.getNomeCategoriaJuridica());
        CategoriaJuridicaEntity categoriaJuridica = new CategoriaJuridicaEntity();
        categoriaJuridica.setNomeCategoriaJuridica(cadastraCategoriaJuridicaDto.getNomeCategoriaJuridica());
        categoriaJuridica.setCodigoCategoriaJuridica(cadastraCategoriaJuridicaDto.getCodigoCategoriaJuridica());

        try {
            categoriaJuridica.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar categoria jurídica");
        }

        return new MensagemResposta(categoriaJuridica.getIdCategoriaJuridica(),
                "Categoria Jurídica cadastrada com sucesso");
    }

    public void buscaCategoriaJuridicaPeloNome(String nomeCategoriaJuridica) {
        Optional<CategoriaJuridicaEntity> categoriaJuridica =
                categoriaJuridicaRepository.buscaCategoriaPeloNome(nomeCategoriaJuridica);

        if (categoriaJuridica.isPresent()) {
            throw new ErroCadastralException("Categoria Jurídica já cadastrada");
        }
    }

    public CategoriaJuridicaEntity buscaCategoriaJuridicaPeloId(Long idCategoriaJuridica) {
        Optional<CategoriaJuridicaEntity> categoriaJuridica = categoriaJuridicaRepository.findByIdOptional(idCategoriaJuridica);

        return categoriaJuridica.orElseThrow(() -> new ErroCadastralException("Categoria não cadastrada"));

    }

}
