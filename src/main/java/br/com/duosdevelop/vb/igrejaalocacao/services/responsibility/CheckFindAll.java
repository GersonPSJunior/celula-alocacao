package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;

import java.util.List;

public class CheckFindAll {

    private MembroRepository repository;
    private DispenseChain chain;

    public CheckFindAll(MembroRepository repository) {
        this.repository = repository;

        chain = new FindAllMembroAtivo(repository);
        DispenseChain c2 = new FindAllMembroBatizado(repository);
        DispenseChain c3 = new FindAllMembroAtivoNaoBatizado(repository);
        DispenseChain c4 = new FindAllMembroBatizadoNaoAtivo(repository);
        DispenseChain c5 = new FindAllMembro(repository);

        chain.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(c4);
        c4.setNextChain(c5);
    }

    public List<Membro> check(String ativo, String batizado){
        if(ativo.equals("sim") && batizado.equals("sim"))
            return repository.findAllByAtivoTrueAndBatizadoTrue();
        return chain.listResult(ativo, batizado);
    }
}
