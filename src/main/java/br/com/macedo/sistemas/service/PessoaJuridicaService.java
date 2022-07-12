package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.EnderecoEntity;
import br.com.macedo.sistemas.domain.aggregate.PessoaJuridicaEntity;
import br.com.macedo.sistemas.domain.aggregate.TipoEnderecoEntity;
import br.com.macedo.sistemas.domain.dto.CadastraPessoaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListaPessoaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.PessoaJuridicaRepository;
import org.hibernate.ObjectNotFoundException;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class PessoaJuridicaService {

    private final static Logger LOGGER = Logger.getLogger(PessoaJuridicaService.class);

    @Inject
    TipoEnderecoService tipoEnderecoService;

    @Inject
    EnderecoService enderecoService;

    @Inject
    PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<ListaPessoaJuridicaDto> listaPessoaJuridica() {
        List<ListaPessoaJuridicaDto> listaPJResponse = new ArrayList<>();

        List<PessoaJuridicaEntity> listaPJ;
        try {
            listaPJ = PessoaJuridicaEntity.listAll();
        } catch (PersistenceException e) {
            throw new ObjectNotFoundException(1, "");
        }

        for(PessoaJuridicaEntity pessoaJuridica :  listaPJ) {
            ListaPessoaJuridicaDto listaPessoaJuridicaDto = new ListaPessoaJuridicaDto(pessoaJuridica);

            listaPJResponse.add(listaPessoaJuridicaDto);
        }

        return listaPJResponse;
    }

    @Transactional
    public MensagemResposta validaCadastroPessoaJuridica(CadastraPessoaJuridicaDto cadastraPessoaJuridicaDto) {
        validaTipoEndereco(cadastraPessoaJuridicaDto.getEndereco().getTipoEndereco());

        validaPessoaJuridica(cadastraPessoaJuridicaDto);

        EnderecoEntity enderecoEntity = enderecoService.cadastraEndereco(cadastraPessoaJuridicaDto.getEndereco());

        PessoaJuridicaEntity pessoaJuridicaEntity = cadastraPessoaJuridica(cadastraPessoaJuridicaDto, enderecoEntity);

        return new MensagemResposta(pessoaJuridicaEntity.getIdPessoaJuridica(), "Pessoa Cadastrada");
    }

    @Transactional
    private PessoaJuridicaEntity cadastraPessoaJuridica(CadastraPessoaJuridicaDto cadastraPessoaJuridicaDto,
                                                        EnderecoEntity enderecoEntity) {
        PessoaJuridicaEntity pessoaJuridica = new PessoaJuridicaEntity();
        pessoaJuridica.setDataAbertura(cadastraPessoaJuridicaDto.getDataAbertura());
        pessoaJuridica.setCnpj(cadastraPessoaJuridicaDto.getCnpj());
        pessoaJuridica.setNomeEmpresarial(cadastraPessoaJuridicaDto.getNomeEmpresarial());
        pessoaJuridica.setNomeFantasia(cadastraPessoaJuridicaDto.getNomeFantasia());
        pessoaJuridica.setEndereco(enderecoEntity);

        try {
            pessoaJuridica.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar pessoa juridica");
        }

        return pessoaJuridica;
    }

    private void validaPessoaJuridica(CadastraPessoaJuridicaDto cadastraPessoaJuridicaDto) {
        //TODO melhorar esse codigo
        PessoaJuridicaEntity pessoaJuridica = pesquisaPessoaJuridicaPeloCnpj(cadastraPessoaJuridicaDto.getCnpj());
        if(Objects.equals(pessoaJuridica.getCnpj(), cadastraPessoaJuridicaDto.getCnpj())) {
            throw new ErroCadastralException("CNPJ já cadastrado");
        }

    }

    private void validaTipoEndereco(TipoEnderecoEntity tipoEndereco) {
        tipoEnderecoService.pesquisaTipoEndereco(tipoEndereco.getIdTipoEndereco());
    }

    private PessoaJuridicaEntity pesquisaPessoaJuridicaPeloCnpj(String cnpj) {
        Optional<PessoaJuridicaEntity> pessoaJuridicaEntity = pessoaJuridicaRepository.findByCnpj(cnpj);

        return pessoaJuridicaEntity.orElse(new PessoaJuridicaEntity());
    }
}
