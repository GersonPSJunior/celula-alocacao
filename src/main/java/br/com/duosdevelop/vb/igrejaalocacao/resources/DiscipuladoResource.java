package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.dto.DiscipuladoDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewDiscipuladoDTO;
import br.com.duosdevelop.vb.igrejaalocacao.event.CreateResourceEvent;
import br.com.duosdevelop.vb.igrejaalocacao.services.DiscipuladoService;
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
@RequestMapping(value = "/discipulados")
public class DiscipuladoResource {

    @Autowired
    private DiscipuladoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DiscipuladoDTO>> findAll(){
        List<Discipulado> discipulados = service.findAll();
            List<DiscipuladoDTO> discipuladoDTOList = discipulados.stream().map(DiscipuladoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(discipuladoDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Discipulado> find(@PathVariable Long id){
        Discipulado discipulado = service.find(id);
        return ResponseEntity.ok().body(discipulado);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Discipulado> insert(@Valid @RequestBody NewDiscipuladoDTO newDiscipuladoDTO, HttpServletResponse response){
        Discipulado discipulado = service.insert(newDiscipuladoDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, discipulado.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(discipulado);
    }
}
