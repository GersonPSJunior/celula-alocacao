package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/membros")
public class MembroResource {

    @Autowired
    private MembroService service;

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
}
