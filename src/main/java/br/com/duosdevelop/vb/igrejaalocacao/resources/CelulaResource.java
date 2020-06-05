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
        return ResponseEntity.ok(celulaDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Celula> find(@PathVariable Long id){
        Celula celula = service.find(id);
        return ResponseEntity.ok(celula);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Celula> insert(@Valid @RequestBody NewCelulaDTO newCelulaDTO, HttpServletResponse response) throws Exception {
        Celula celula = service.insert(newCelulaDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, celula.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(celula);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody NewCelulaDTO newCelulaDTO,
                                       @PathVariable Long id) throws Exception {
        service.update(id, newCelulaDTO.toDomain());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}/membro", method = RequestMethod.PUT)
    public ResponseEntity<Void> insertMembro(@RequestBody List<Long> idMembros,
                                             @PathVariable Long id){
        service.insertMembro(id, idMembros);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
