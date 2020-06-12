package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import br.com.duosdevelop.vb.igrejaalocacao.services.responsibility.CheckFindAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private CelulaRepository celulaRepository;

    @Autowired
    private BCryptPasswordEncoder be;

    public List<Membro> findAll(String ativo, String batizado) {
        return new CheckFindAll(repository).check(ativo, batizado);
    }

    @Transactional
    public Membro insert(Membro membro) {
        membro.getPessoa().setSenha(be.encode(membro.getPessoa().getSenha()));
        Pessoa pessoa = pessoaRepository.findByCpf(membro.getPessoa().getCpf())
                .orElse(pessoaRepository.save(membro.getPessoa()));
        membro.setPessoa(pessoa);
        enderecoRepository.saveAll(membro.getPessoa().getEnderecos());
        membro = repository.save(membro);
        return  membro;
    }

    public Membro find(Long id) {
        Optional<Membro> membro = repository.findById(id);
        return membro.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id:"+ id
                +", Type:"+ Membro.class.getName()));
    }

    @Transactional
    public Membro update(Long id, Membro updateMembro){
        updateMembro.getPessoa().setSenha(be.encode(updateMembro.getPessoa().getSenha()));
        Membro membro = find(id);
        membro.replaceValues(updateMembro);
        return repository.save(membro);
    }

    public void delete(Long id){
        find(id);
        repository.deleteById(id);
    }

    @Transactional
    public Membro updateCelula(Long id, Long idCelula) {
        Celula celula = celulaRepository.findById(idCelula)
                .orElseThrow(() -> new ObjectNotFoundException("Object not found! Id:" + id
                        + ", Type:" + Celula.class.getName()));
        Membro membro = find(id);
        membro.setCelula(new Celula(idCelula));
        celula.getMembros().add(membro);
        celulaRepository.save(celula);
        return repository.save(membro);
    }
}
