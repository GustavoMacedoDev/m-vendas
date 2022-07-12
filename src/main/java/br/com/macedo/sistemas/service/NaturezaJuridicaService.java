package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import br.com.macedo.sistemas.domain.aggregate.NaturezaJuridicaEntity;
import br.com.macedo.sistemas.domain.dto.CadastraNaturezaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListagemNaturezaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.NaturezaJuridicaRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class NaturezaJuridicaService {

    private final static Logger LOGGER = Logger.getLogger(TipoEnderecoService.class);

    @Inject
    NaturezaJuridicaRepository naturezaJuridicaRepository;

    @Inject
    CategoriaJuridicaService categoriaJuridicaService;

    public List<ListagemNaturezaJuridicaDto> listaNaturezaJuridica() {
        List<NaturezaJuridicaEntity> listaNaturezaJuridica = NaturezaJuridicaEntity.listAll();
        List<ListagemNaturezaJuridicaDto> listaResponse = new ArrayList<>();

        for(NaturezaJuridicaEntity naturezaJuridica : listaNaturezaJuridica) {
            ListagemNaturezaJuridicaDto listagemNaturezaJuridicaDto = new ListagemNaturezaJuridicaDto(naturezaJuridica);

            listaResponse.add(listagemNaturezaJuridicaDto);
        }

        return listaResponse;

    }

    @Transactional
    public MensagemResposta cadastraNaturezaJuridica(CadastraNaturezaJuridicaDto cadastraNaturezaJuridicaDto) {
        CategoriaJuridicaEntity categoriaJuridica = categoriaJuridicaService.
                buscaCategoriaJuridicaPeloId(cadastraNaturezaJuridicaDto.getCategoriaJuridicaDto().getIdCategoriaJuridica());

        Optional<NaturezaJuridicaEntity> naturezaJuridicaEntity = buscaNaturezaPeloNome(cadastraNaturezaJuridicaDto);
        if(naturezaJuridicaEntity.isPresent()) {
            throw new ErroCadastralException("Natureza Juridica ja cadastrada");
        }

        NaturezaJuridicaEntity naturezaJuridica = new NaturezaJuridicaEntity();
        naturezaJuridica.setNomeNaturezaJuridica(cadastraNaturezaJuridicaDto.getNomeNaturezaJuridica());
        naturezaJuridica.setCodigoNaturezaJuridica(cadastraNaturezaJuridicaDto.getCodigoNaturezaJuridica());
        naturezaJuridica.setCategoriaJuridica(categoriaJuridica);

        try {
            naturezaJuridica.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar natureza juridica" + e.getMessage());
        }

        return new MensagemResposta(naturezaJuridica.getIdNaturezaJuridica(),
                "Natureza Jurídica cadastrada com sucesso!");
    }

    private Optional<NaturezaJuridicaEntity> buscaNaturezaPeloNome(CadastraNaturezaJuridicaDto cadastraNaturezaJuridicaDto) {

        return naturezaJuridicaRepository.buscaNaturezaPeloNome(cadastraNaturezaJuridicaDto);
    }
}
