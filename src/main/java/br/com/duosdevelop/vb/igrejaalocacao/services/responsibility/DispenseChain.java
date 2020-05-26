package br.com.duosdevelop.vb.igrejaalocacao.services.responsibility;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;

import java.util.List;

public interface DispenseChain {
    void setNextChain(DispenseChain next);
    List<Membro> listResult(String ativo, String batizado);
}
