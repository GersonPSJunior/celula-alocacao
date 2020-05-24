package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulador;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.DiscipuladorRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DiscipuladorService {

    @Autowired
    private DiscipuladorRepository repository;

    public List<Discipulador> findAll(){
        return repository.findAll();
    }

    public Discipulador find(Long id){
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo:" + Discipulador.class.getName()));
    }
}
