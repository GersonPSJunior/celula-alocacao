package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.CelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.services.CelulaService;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/celulas")
public class CelulaResource {

    @Autowired
    private CelulaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CelulaDTO>> findAll(){
        List<Celula> celulas = service.findAll();
        List<CelulaDTO> celulaDTOList = celulas.stream().map(CelulaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(celulaDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Celula> find(@PathVariable Integer id){
        Celula celula = service.find(id);
        return ResponseEntity.ok().body(celula);
    }
}
