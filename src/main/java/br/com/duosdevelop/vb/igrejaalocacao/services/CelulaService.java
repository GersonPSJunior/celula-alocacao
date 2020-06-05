package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CelulaService {

    private final CelulaRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final MembroRepository membroRepository;

    @Autowired
    public CelulaService(CelulaRepository repository, EnderecoRepository enderecoRepository, MembroRepository membroRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.membroRepository = membroRepository;
    }

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

    @Transactional
    public Celula update(Long id, Celula celulaUpdate) {
        Celula celula = find(id);
        celula.setNome(celulaUpdate.getNome());
        celula.setLider(celulaUpdate.getLider());
        celula.setHorario(celulaUpdate.getHorario());
        celula.setDia(celulaUpdate.getDia());
        celula.setEndereco(celulaUpdate.getEndereco());
        enderecoRepository.save(celula.getEndereco());
        return repository.save(celula);
    }

    @Transactional
    public Celula insertMembro(Long id, List<Long> idMembros){
        Celula celula = find(id);
        List<Membro> membros = membroRepository.findAllById(idMembros);
        membros.forEach(membro -> membro.setCelula(celula));
        celula.setMembros(membroRepository.saveAll(membros));
        return celula;
    }

    public void delete(Long id){
        Celula celula = find(id);
        repository.delete(celula);
    }
}
