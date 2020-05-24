package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewDiscipuladoDTO;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.*;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscipuladoService {

    @Autowired
    private DiscipuladoRepository repository;

    @Autowired
    private RedeRepository redeRepository;

    @Autowired
    private DiscipuladorRepository discipuladorRepository;

    @Autowired
    private CelulaRepository celulaRepository;

    @Transactional
    public Discipulado insert(Discipulado discipulado){
        return repository.save(discipulado);
    }
    public List<Discipulado> findAll(){
        return repository.findAll();
    }

    public Discipulado find(Long id){
        Optional<Discipulado> discipulado = repository.findById(id);
        return discipulado.orElseThrow(() -> new ObjectNotFoundException("Discipulado não encontrado"));
    }
}
