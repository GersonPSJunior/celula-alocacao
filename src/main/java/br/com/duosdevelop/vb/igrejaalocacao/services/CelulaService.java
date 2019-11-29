package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CelulaService {

    @Autowired
    private CelulaRepository repository;

    public List<Celula> findAll() {
        return repository.findAll();
    }

    public Celula find(Integer id) {
        Optional<Celula> celula = repository.findById(id);
        return celula.orElseThrow(() -> new ObjectNotFoundException("Célula não encontrada"));
    }
}
