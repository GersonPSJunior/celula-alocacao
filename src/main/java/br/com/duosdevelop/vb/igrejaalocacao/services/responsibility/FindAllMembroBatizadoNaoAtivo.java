package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;

import java.util.List;

public class FindAllMembroBatizadoNaoAtivo implements DispenseChain {
    private DispenseChain chain;
    private MembroRepository repository;
    public FindAllMembroBatizadoNaoAtivo(MembroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextChain(DispenseChain next) {
        chain = next;
    }

    @Override
    public List<Membro> listResult(String ativo, String batizado) {
        if(ativo.equals("nao") && batizado.equals("sim"))
            return repository.findAllByAtivoFalseAndBatizadoTrue();
        return chain.listResult(ativo, batizado);
    }
}
