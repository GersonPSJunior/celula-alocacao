package br.com.duosdevelop.vb.igrejaalocacao.resources;


import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findCpf(@RequestParam String cpf){
        Pessoa pessoaResponse = service.findCpf(cpf);
        return ResponseEntity.ok(pessoaResponse);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/permissao")
    @PreAuthorize("hasAuthority('ROLE_INSERIR_PERMISSAO_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> insertPermissao(@Param("doc") String cpf, @RequestBody List<Long> permissoes){
        service.insertPermissoes(cpf, permissoes);
        return ResponseEntity.noContent().build();
    }
}
