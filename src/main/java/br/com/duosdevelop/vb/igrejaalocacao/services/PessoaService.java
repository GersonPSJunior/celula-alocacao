package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public Pessoa findCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF: " + cpf + ", Tipo:" + Pessoa.class.getName()));
    }
}
