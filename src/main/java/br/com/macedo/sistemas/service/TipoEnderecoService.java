package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.EnderecoEntity;
import br.com.macedo.sistemas.domain.aggregate.TipoEnderecoEntity;
import br.com.macedo.sistemas.domain.dto.AlteraTipoEnderecoDto;
import br.com.macedo.sistemas.domain.dto.CadastraTipoEnderecoDto;
import br.com.macedo.sistemas.domain.dto.ListaTipoEnderecoDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TipoEnderecoService {

    private final static Logger LOGGER = Logger.getLogger(TipoEnderecoService.class);

    @Inject
    EnderecoService enderecoService;

    public List<ListaTipoEnderecoDto> listaTipoEndereco() {
        List<ListaTipoEnderecoDto> listaResponse = new ArrayList<>();
        List<TipoEnderecoEntity> listaTipoEndereco;

        try {
            listaTipoEndereco = TipoEnderecoEntity.listAll();

        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao buscar tipos de endereço");
        }

        for (TipoEnderecoEntity tipoEndereco : listaTipoEndereco) {
            ListaTipoEnderecoDto listaTipoEnderecoDto = new ListaTipoEnderecoDto(tipoEndereco);

            listaResponse.add(listaTipoEnderecoDto);
        }

        return listaResponse;
    }

    @Transactional
    public MensagemResposta cadastraTipoEndereco(CadastraTipoEnderecoDto cadastraTipoEnderecoDto) {
        TipoEnderecoEntity tipoEnderecoEntity = new TipoEnderecoEntity();
        tipoEnderecoEntity.setNomeTipoEndereco(cadastraTipoEnderecoDto.getNomeTipoEndereco().toUpperCase());

        try {
            tipoEnderecoEntity.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException(
                    "Erro ao cadastrar tipo de endereço, verifique se esse tipo já foi cadastrado");
        }

        return new MensagemResposta(tipoEnderecoEntity.getIdTipoEndereco(), "Cadastro realizado com suceso");
    }


    @Transactional
    public MensagemResposta alteraTipoEndereco(Long codigoTipoEndereco, AlteraTipoEnderecoDto alteraTipoEnderecoDto) {
        TipoEnderecoEntity tipoEnderecoEntity = pesquisaTipoEndereco(codigoTipoEndereco);

        List<EnderecoEntity> listaEnderecos = validaVinculoComEndereco(tipoEnderecoEntity.getIdTipoEndereco());
        if(listaEnderecos.isEmpty()) {
            tipoEnderecoEntity.setNomeTipoEndereco(alteraTipoEnderecoDto.getNomeTipoEndereco().toUpperCase());

            try {
                tipoEnderecoEntity.persistAndFlush();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new ErroCadastralException("Erro ao alterar tipo de endereço");
            }

        } else {
            throw new ErroCadastralException("Tipo não pode ser alterado por possuir vinculos com endereco");
        }

        return new MensagemResposta(tipoEnderecoEntity.getIdTipoEndereco(), "Tipo alterado com sucesso");

    }

    private List<EnderecoEntity> validaVinculoComEndereco(Long codigoTipoEndereco) {

        return enderecoService.listaEnderecosPorIdTipo(codigoTipoEndereco);
    }


    public TipoEnderecoEntity pesquisaTipoEndereco(Long codigoTipoEndereco) {
        Optional<TipoEnderecoEntity> tipoEnderecoEntity = TipoEnderecoEntity.findByIdOptional(codigoTipoEndereco);

        return tipoEnderecoEntity.orElseThrow(() -> new ErroCadastralException("Tipo de endereço não encontrado"));
    }

    @Transactional
    public MensagemResposta deletaTipoEndereco(Long codigoTipoEndereco) {
        buscaTipoEnderecoPorId(codigoTipoEndereco);
        List<EnderecoEntity> listaEnderecos = validaVinculoComEndereco(codigoTipoEndereco);
        if (listaEnderecos.isEmpty()) {
            TipoEnderecoEntity.deleteById(codigoTipoEndereco);
        } else {
            throw new ErroCadastralException("O Tipo de Endereço possui vinculo com endereços " + listaEnderecos.toString());
        }

        return new MensagemResposta(codigoTipoEndereco, "Tipo deletado com sucesso");

    }

    private void buscaTipoEnderecoPorId(Long codigoTipoEndereco) {
        Optional<TipoEnderecoEntity> tipoEnderecoEntity = TipoEnderecoEntity.findByIdOptional(codigoTipoEndereco);

        tipoEnderecoEntity.orElseThrow(() -> new ErroCadastralException("Tipo Endereço não encontrado"));
    }
}
