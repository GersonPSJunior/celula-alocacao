package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.UpdateMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.event.CreateResourceEvent;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/membros")
public class MembroResource {

    private final MembroService service;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public MembroResource(MembroService service, ApplicationEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Membro>> findAll(){
        List<Membro> membros = service.findAll();
        return ResponseEntity.ok().body(membros);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membro> find(@PathVariable Long id){
        Membro membro = service.find(id);
        return ResponseEntity.ok().body(membro);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Membro> insert(@Valid @RequestBody NewMembroDTO newMembroDTO, HttpServletResponse response) throws Exception {
        Membro membro = service.insert(newMembroDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, membro.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(membro);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateMembroDTO updateMembroDTO, @PathVariable Long id, HttpServletResponse response) throws Exception {
        Membro membro = service.update(id, updateMembroDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, membro.getId()));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
