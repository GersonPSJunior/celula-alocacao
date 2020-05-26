package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;

import java.util.List;

public class FindAllMembroAtivo implements DispenseChain {
    private DispenseChain chain;
    private MembroRepository repository;
    public FindAllMembroAtivo(MembroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextChain(DispenseChain next) {
        chain = next;
    }

    @Override
    public List<Membro> listResult(String ativo, String batizado) {
        if(ativo.equals("sim") && batizado.equals(""))
            return repository.findAllByAtivoTrue();
        return chain.listResult(ativo, batizado);
    }
}
