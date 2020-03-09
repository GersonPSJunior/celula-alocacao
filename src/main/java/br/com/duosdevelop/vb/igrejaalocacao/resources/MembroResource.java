package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewMembroDTO;
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

    @Autowired
    private MembroService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Membro>> findAll(){
        List<Membro> membros = service.findAll();
        return ResponseEntity.ok().body(membros);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membro> find(@PathVariable Integer id){
        Membro membro = service.find(id);
        return ResponseEntity.ok().body(membro);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Membro> insert(@Valid @RequestBody NewMembroDTO newMembroDTO, HttpServletResponse response) throws Exception {
        Membro membro = service.fromDTO(newMembroDTO);
        Membro membroResult = service.insert(membro);
        publisher.publishEvent(new CreateResourceEvent(this, response, membroResult.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(membroResult);
    }
}
