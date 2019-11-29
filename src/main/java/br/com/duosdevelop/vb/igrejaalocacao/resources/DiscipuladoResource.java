package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.dto.DiscipuladoDTO;
import br.com.duosdevelop.vb.igrejaalocacao.services.DiscipuladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/discipulados")
public class DiscipuladoResource {

    @Autowired
    private DiscipuladoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DiscipuladoDTO>> findAll(){
        List<Discipulado> discipulados = service.findAll();
            List<DiscipuladoDTO> discipuladoDTOList = discipulados.stream().map(DiscipuladoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(discipuladoDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Discipulado> find(@PathVariable Integer id){
        Discipulado discipulado = service.find(id);
        return ResponseEntity.ok().body(discipulado);
    }
}
