package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.dto.CelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewCelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.event.CreateResourceEvent;
import br.com.duosdevelop.vb.igrejaalocacao.services.CelulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/celulas")
public class CelulaResource {

    @Autowired
    private CelulaService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CelulaDTO>> findAll(){
        List<Celula> celulas = service.findAll();
        List<CelulaDTO> celulaDTOList = celulas.stream().map(CelulaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(celulaDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Celula> find(@PathVariable Long id){
        Celula celula = service.find(id);
        return ResponseEntity.ok().body(celula);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Celula> insert(@Valid @RequestBody NewCelulaDTO newCelulaDTO, HttpServletResponse response) throws Exception {
        Celula celula = service.insert(newCelulaDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, celula.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(celula);
    }
}
