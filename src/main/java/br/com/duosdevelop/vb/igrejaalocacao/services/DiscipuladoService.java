package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Rede;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewDiscipuladoDTO;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.DiscipuladoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.RedeRepository;
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
    private MembroRepository membroRepository;

    @Autowired
    private CelulaRepository celulaRepository;

    @Transactional
    public Discipulado insert(Discipulado discipulado){
        discipulado.setId(null);
        return repository.save(discipulado);
    }
    public List<Discipulado> findAll(){
        return repository.findAll();
    }

    public Discipulado find(Integer id){
        Optional<Discipulado> discipulado = repository.findById(id);
        return discipulado.orElseThrow(() -> new ObjectNotFoundException("Discipulado não encontrado"));
    }

    public Discipulado from(NewDiscipuladoDTO newDiscipuladoDTO) {
        Discipulado discipulado = new Discipulado(null, newDiscipuladoDTO.getNome(),
                redeRepository.findById(newDiscipuladoDTO.getIdRede()).orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id: " + newDiscipuladoDTO.getIdRede() + ", Tipo:" + Rede.class.getName())),
                membroRepository.findById(newDiscipuladoDTO.getIdDiscipulador()).orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! Id: " + newDiscipuladoDTO.getIdDiscipulador() + ", Tipo:" + Membro.class.getName())));
        if (newDiscipuladoDTO.getIdCelula() != null){
            discipulado.setCelulas(newDiscipuladoDTO.getIdCelula().stream().map(id -> celulaRepository.findById(id).orElseThrow(() ->
                    new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo:" + Celula.class.getName())))
                    .collect(Collectors.toList()));
        }
        return discipulado;

    }
}
