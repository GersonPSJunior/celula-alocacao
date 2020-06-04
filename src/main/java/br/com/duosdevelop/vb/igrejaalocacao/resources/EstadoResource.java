package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;
import br.com.duosdevelop.vb.igrejaalocacao.event.CreateResourceEvent;
import br.com.duosdevelop.vb.igrejaalocacao.services.CidadeService;
import br.com.duosdevelop.vb.igrejaalocacao.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    private EstadoService service;
    private CidadeService cidadeService;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public EstadoResource(EstadoService service, CidadeService cidadeService, ApplicationEventPublisher eventPublisher) {
        this.service = service;
        this.cidadeService = cidadeService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estado>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Estado> findId(@PathVariable Long id){
        return  ResponseEntity.ok(service.findId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Estado> insert(@RequestBody Estado estado, HttpServletResponse response){
        Estado estadoResult = service.insert(estado);
        eventPublisher.publishEvent(new CreateResourceEvent(this, response, estadoResult.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoResult);
    }

    @RequestMapping(path = "/{id}/cidade",method = RequestMethod.PUT)
    public ResponseEntity<Void> insertCidade(@RequestBody String cidade,
                                               @PathVariable("id") Long id){
        Estado estado = service.findId(id);
        cidadeService.insert(new Cidade(cidade, estado));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll(){
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
