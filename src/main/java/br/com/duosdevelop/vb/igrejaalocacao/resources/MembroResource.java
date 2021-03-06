package br.com.duosdevelop.vb.igrejaalocacao.resources;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.CelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.MembroOutputDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.dto.UpdateMembroDTO;
import br.com.duosdevelop.vb.igrejaalocacao.event.CreateResourceEvent;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEMBRO') and #oauth2.hasScope('read')")
    public ResponseEntity<List<Membro>> findAll(
            @RequestParam(name = "ativo", defaultValue = "") String ativo,
            @RequestParam(name = "batizado", defaultValue = "") String batizado){
        List<Membro> membros = service.findAll(ativo, batizado);
        return ResponseEntity.ok().body(membros);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEMBRO') and #oauth2.hasScope('read')")
    public ResponseEntity<MembroOutputDTO> find(@PathVariable Long id){
        Membro membro = service.find(id);
        return ResponseEntity.ok().body(new MembroOutputDTO(membro.getPessoa(),
                membro.getCelula() != null ? new CelulaDTO(membro.getCelula()) : null,
                membro.getAtivo(), membro.getBatizado()));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEMBRO') and #oauth2.hasScope('write')")
    public ResponseEntity<Membro> insert(@Valid @RequestBody NewMembroDTO newMembroDTO, HttpServletResponse response) throws Exception {
        Membro membro = service.insert(newMembroDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, membro.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(membro);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_MEMBRO') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateMembroDTO updateMembroDTO, @PathVariable Long id, HttpServletResponse response) throws Exception {
        Membro membro = service.update(id, updateMembroDTO.toDomain());
        publisher.publishEvent(new CreateResourceEvent(this, response, membro.getId()));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETAR_MEMBRO') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/celula")
    @PreAuthorize("hasAuthority('ROLE_ASSOCIAR_CELULA_MEMBRO') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> insertCelula(@PathVariable Long id,
                                                        @RequestBody Long idCelula,
                                                        HttpServletResponse response){
        Membro membro = service.updateCelula(id, idCelula);
        publisher.publishEvent(new CreateResourceEvent(this, response, membro.getId()));
        return ResponseEntity.noContent().build();
    }
}
