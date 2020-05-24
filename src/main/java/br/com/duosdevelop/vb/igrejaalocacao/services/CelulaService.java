package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CelulaService {

    @Autowired
    private CelulaRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Celula> findAll() {
        return repository.findAll();
    }

    public Celula find(Long id) {
        Optional<Celula> celula = repository.findById(id);
        return celula.orElseThrow(() -> new ObjectNotFoundException("Célula não encontrada"));
    }

    @Transactional
    public Celula insert(Celula celula){
        enderecoRepository.save(celula.getEndereco());
        Celula celulaResult = repository.save(celula);
        return celulaResult;
    }

}
