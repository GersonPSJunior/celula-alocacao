package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;

import java.io.Serializable;

public class MembroOutputDTO implements Serializable {

    private Pessoa pessoa;

    private CelulaDTO celula;

    private Boolean ativo;

    private Boolean batizado;

    public MembroOutputDTO(Pessoa pessoa, CelulaDTO celula, Boolean ativo, Boolean batizado) {
        this.pessoa = pessoa;
        this.celula = celula;
        this.ativo = ativo;
        this.batizado = batizado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public CelulaDTO getCelula() {
        return celula;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Boolean getBatizado() {
        return batizado;
    }
}
