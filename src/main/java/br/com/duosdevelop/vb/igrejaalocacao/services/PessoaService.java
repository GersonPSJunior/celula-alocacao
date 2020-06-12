package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Permissao;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PermissaoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public Pessoa findCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF: " + cpf + ", Tipo:" + Pessoa.class.getName()));
    }

    public void insertPermissoes(String cpf, List<Long> permissoes) {
        Pessoa pessoa = findCpf(cpf);
        pessoa.setPermissoes(permissoes.stream().map(id -> new Permissao(id, null)).collect(Collectors.toList()));
        repository.save(pessoa);
    }
}
