package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Membro extends EntityBase<Membro> {

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    private Boolean ativo;

    private Boolean batizado;

    public Membro() {
    }

    public Membro(Long id) {
        this.id = id;
    }

    public Membro(Pessoa pessoa, Boolean batizado, Boolean ativo) {
        this.pessoa = pessoa;
        this.batizado = batizado;
        this.ativo = ativo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getBatizado() {
        return batizado;
    }

    public void setBatizado(Boolean batizado) {
        this.batizado = batizado;
    }

    @Override
    public String toString() {
        return "Membro{" +
                "id=" + getId() +
                "pessoa=" + pessoa +
                ", celula=" + celula +
                ", ativo=" + ativo +
                ", batizado=" + batizado +
                '}';
    }

    public void replaceValues(Membro membroReplace) {
        pessoa.replaceValues(membroReplace.pessoa);
        ativo = membroReplace.getAtivo();
        batizado = membroReplace.getBatizado();
    }
}
