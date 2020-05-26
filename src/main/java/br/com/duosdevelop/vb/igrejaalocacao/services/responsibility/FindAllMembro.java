package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;

import java.util.List;

public class FindAllMembro implements DispenseChain {
    private DispenseChain chain;
    private MembroRepository repository;
    public FindAllMembro(MembroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextChain(DispenseChain next) {
        chain = next;
    }

    @Override
    public List<Membro> listResult(String ativo, String batizado) {
        return repository.findAll();
    }
}
