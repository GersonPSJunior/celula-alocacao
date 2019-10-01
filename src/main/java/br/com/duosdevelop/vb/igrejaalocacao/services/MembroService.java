package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository repository;

    public List<Membro> findAll() {
        return repository.findAll();
    }

    public Membro find(Integer id) {
        Optional<Membro> membro = repository.findById(id);
        return membro.orElseThrow(() -> new ObjectNotFoundException("Membro não encontrado"));
    }
}
