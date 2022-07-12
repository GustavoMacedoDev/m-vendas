package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.EnderecoEntity;
import br.com.macedo.sistemas.domain.dto.EnderecoEntityDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.repository.EnderecoRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    private final static Logger LOGGER = Logger.getLogger(PessoaJuridicaService.class);

    @Transactional
    public EnderecoEntity cadastraEndereco(EnderecoEntityDto enderecoDto) {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setNumero(enderecoDto.getNumero());
        enderecoEntity.setLogradouro(enderecoDto.getLogradouro());
        enderecoEntity.setBairro(enderecoDto.getBairro());
        enderecoEntity.setCep(enderecoDto.getCep());
        enderecoEntity.setComplemento(enderecoDto.getComplemento());
        enderecoEntity.setTipoEndereco(enderecoDto.getTipoEndereco());

        try{
            enderecoEntity.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar endereço");
        }

        return enderecoEntity;
    }

    public List<EnderecoEntity> listaEnderecosPorIdTipo(Long codigoTipoEndereco) {
        return enderecoRepository.listaEnderecosPorIdTipo(codigoTipoEndereco);
    }

}
