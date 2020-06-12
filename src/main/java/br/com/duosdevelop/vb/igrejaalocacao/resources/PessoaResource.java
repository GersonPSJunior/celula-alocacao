package br.com.duosdevelop.vb.igrejaalocacao.resources;


import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
