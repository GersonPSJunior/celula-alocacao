package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CidadeRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    @Autowired
    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> findAll(){
        return repository.findAll();
    }

    public Cidade findId(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Obaject not found! id: "+
                id +", Type: "+ Cidade.class.getName()));
    }

    public Cidade insert(Cidade cidade){
        return repository.save(cidade);
    }
}
