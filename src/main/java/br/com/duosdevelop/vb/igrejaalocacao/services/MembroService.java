package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.UpdateMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import br.com.duosdevelop.vb.igrejaalocacao.services.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CelulaRepository celulaRepository;

    @Autowired
    private DateUtil dateUtil;

    public List<Membro> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Membro insert(Membro membro) {
        membro.setId(null);

        Pessoa pessoa = pessoaRepository.findByCpf(membro.getPessoa().getCpf())
                .orElse(pessoaRepository.save(membro.getPessoa()));
        membro.setPessoa(pessoa);

        enderecoRepository.saveAll(membro.getPessoa().getEnderecos());
        membro = repository.save(membro);
        return  membro;
    }

    public Membro find(Integer id) {
        Optional<Membro> membro = repository.findById(id);
        return membro.orElseThrow(() -> new ObjectNotFoundException("Membro não encontrado"));
    }

    @Transactional
    public Membro update(Membro membro){
        find(membro.getId());
        pessoaRepository.save(membro.getPessoa());
        return repository.save(membro);
    }

    public void delete(Integer id){
        find(id);
        repository.deleteById(id);
    }

    public Membro fromDTO(NewMembroDTO newMembroDTO) throws Exception {

        Pessoa pessoa = new Pessoa(newMembroDTO.getNome(), dateUtil.toDate(newMembroDTO.getNascimento()), newMembroDTO.getCpf());
        Membro membro = new Membro(pessoa, newMembroDTO.getBatizado(), newMembroDTO.getAtivo());
        if(newMembroDTO.getCelula() != null) {
            membro.setCelula(celulaRepository.findById(newMembroDTO.getCelula()).orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + newMembroDTO.getCelula() + ", Tipo:" + Membro.class.getName())));
        }
        Cidade cidade = new Cidade(newMembroDTO.getEndereco().getCidade(), null, new Estado(newMembroDTO.getEndereco().getEstado(), null));
        Endereco endereco = new Endereco(newMembroDTO.getEndereco().getRua(), newMembroDTO.getEndereco().getNumero(), newMembroDTO.getEndereco().getCep(), cidade);
        endereco.setPessoa(membro.getPessoa());
        if (newMembroDTO.getEndereco().getBairro() != null)
            endereco.setBairro(newMembroDTO.getEndereco().getBairro());
        if (newMembroDTO.getEndereco().getComplemento() != null)
            endereco.setComplemento(newMembroDTO.getEndereco().getComplemento());
        membro.getPessoa().setEnderecos(Arrays.asList(endereco));
        membro.getPessoa().getTelefone().add(newMembroDTO.getTelefone1());
        if (newMembroDTO.getTelefone2() != null)
            membro.getPessoa().getTelefone().add(newMembroDTO.getTelefone2());
        if (newMembroDTO.getTelefone3() != null)
            membro.getPessoa().getTelefone().add(newMembroDTO.getTelefone3());
        return membro;
    }

    public Membro fromDTO(UpdateMembroDTO updateMembroDTO) throws Exception {

        Pessoa pessoaCpf = pessoaRepository.findByCpf(updateMembroDTO.getCpf()).orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! CPF: " + updateMembroDTO.getCpf() + ", Tipo:" + Pessoa.class.getName()));

        Pessoa pessoa = new Pessoa(pessoaCpf.getId(),updateMembroDTO.getNome(), dateUtil.toDate(updateMembroDTO.getNascimento()), updateMembroDTO.getCpf());
        Membro membro = new Membro(pessoa, updateMembroDTO.getBatizado(), updateMembroDTO.getAtivo());
        if(updateMembroDTO.getCelula() != null) {
            membro.setCelula(celulaRepository.findById(updateMembroDTO.getCelula()).orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + updateMembroDTO.getCelula() + ", Tipo:" + Membro.class.getName())));
        }
        Cidade cidade = new Cidade(updateMembroDTO.getEndereco().getCidade(), null, new Estado(updateMembroDTO.getEndereco().getEstado(), null));
        Endereco endereco = new Endereco(updateMembroDTO.getEndereco().getId(), updateMembroDTO.getEndereco().getRua(), updateMembroDTO.getEndereco().getNumero(), updateMembroDTO.getEndereco().getCep(), cidade);
        endereco.setPessoa(membro.getPessoa());
        if (updateMembroDTO.getEndereco().getBairro() != null)
            endereco.setBairro(updateMembroDTO.getEndereco().getBairro());
        if (updateMembroDTO.getEndereco().getComplemento() != null)
            endereco.setComplemento(updateMembroDTO.getEndereco().getComplemento());
        membro.getPessoa().setEnderecos(Arrays.asList(endereco));
        membro.getPessoa().getTelefone().add(updateMembroDTO.getTelefone1());
        if (updateMembroDTO.getTelefone2() != null)
            membro.getPessoa().getTelefone().add(updateMembroDTO.getTelefone2());
        if (updateMembroDTO.getTelefone3() != null)
            membro.getPessoa().getTelefone().add(updateMembroDTO.getTelefone3());
        return membro;
    }
}
