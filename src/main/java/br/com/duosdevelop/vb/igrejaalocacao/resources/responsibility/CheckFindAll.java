package br.com.duosdevelop.vb.igrejaalocacao.resources.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.services.MembroService;

import java.util.List;

public class CheckFindAll {

    private MembroService service;
    private DispenseChain chain;

    public CheckFindAll(MembroService service) {
        this.service = service;
        this.chain = new FindAllMembroAtivo(service);
        DispenseChain c2 = new FindAllMembroBatizado(service);
        DispenseChain c3 = new FindAllMembroAtivoNaoBatizado(service);
        DispenseChain c4 = new FindAllMembroBatizadoNaoAtivo(service);
        DispenseChain c5 = new FindAllMembro(service);

        this.chain.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(c4);
        c4.setNextChain(c5);
    }

    public List<Membro> check(String ativo, String batizado){
        if(ativo.equals("sim") && batizado.equals("sim"))
            return service.findAllAtivoAndBatizado();
        return chain.listResult(ativo, batizado);
    }
}
