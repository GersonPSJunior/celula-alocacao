package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import br.com.duosdevelop.vb.igrejaalocacao.services.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private DateUtil dateUtil;

    public List<Membro> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Membro insert(Membro membro) {
        Pessoa pessoa = pessoaRepository.findByCpf(membro.getPessoa().getCpf())
                .orElse(pessoaRepository.save(membro.getPessoa()));
        membro.setPessoa(pessoa);
        enderecoRepository.saveAll(membro.getPessoa().getEnderecos());
        membro = repository.save(membro);
        return  membro;
    }

    public Membro find(Long id) {
        Optional<Membro> membro = repository.findById(id);
        return membro.orElseThrow(() -> new ObjectNotFoundException("Membro não encontrado"));
    }

    @Transactional
    public Membro update(Long id, Membro updateMembro) throws Exception {
        Membro membro = find(id);
        membro.replaceValues(updateMembro);
        return repository.save(membro);
    }

    public void delete(Long id){
        find(id);
        repository.deleteById(id);
    }
}
