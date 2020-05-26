package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;

import java.util.List;

public class FindAllMembroBatizado implements DispenseChain {

    private DispenseChain chain;
    private MembroRepository repository;
    public FindAllMembroBatizado(MembroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextChain(DispenseChain next) {
        chain = next;
    }

    @Override
    public List<Membro> listResult(String ativo, String batizado) {
        if(ativo.equals("") && batizado.equals("sim"))
            return repository.findAllByBatizadoTrue();
        return chain.listResult(ativo, batizado);
    }

}
