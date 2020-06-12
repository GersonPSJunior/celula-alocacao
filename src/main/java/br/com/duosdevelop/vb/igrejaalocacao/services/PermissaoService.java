package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Permissao;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {
    @Autowired
    private PermissaoRepository repository;

    public List<Permissao> findAll(){
        return repository.findAll();
    }
}
