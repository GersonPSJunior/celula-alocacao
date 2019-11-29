package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.DiscipuladoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscipuladoService {

    @Autowired
    private DiscipuladoRepository repository;

    public List<Discipulado> findAll(){
        return repository.findAll();
    }

    public Discipulado find(Integer id){
        Optional<Discipulado> discipulado = repository.findById(id);
        return discipulado.orElseThrow(() -> new ObjectNotFoundException("Discipulado não encontrado"));
    }
}
