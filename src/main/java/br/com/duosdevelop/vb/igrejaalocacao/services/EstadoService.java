package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EstadoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private EstadoRepository repository;

    @Autowired
    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> findAll(){
        return repository.findAll();
    }

    public Estado findId(Long id){
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Object not found! Id:"+ id +", Type:"+ Estado.class.getName()));
    }
    public Estado insert(Estado estado){
       return repository.save(estado);
    }

    public void delete(Long id){
        repository.delete(findId(id));
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
