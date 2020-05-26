package br.com.duosdevelop.vb.igrejaalocacao.resources.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;

import java.util.List;

public class FindAllMembroAtivoNaoBatizado implements DispenseChain {
    private DispenseChain chain;
    private MembroService service;
    public FindAllMembroAtivoNaoBatizado(MembroService service) {
        this.service = service;
    }

    @Override
    public void setNextChain(DispenseChain next) {
        chain = next;
    }

    @Override
    public List<Membro> listResult(String ativo, String batizado) {
        if(ativo.equals("sim") && batizado.equals("nao"))
            return service.findAllAtivoNaoBatizado();
        return chain.listResult(ativo, batizado);
    }
}
