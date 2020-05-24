package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Discipulado extends EntityBase<Discipulado> {

    private String nome;

    @ManyToOne
    @JoinColumn(name = "rede_id")
    private Rede rede;

    @OneToOne
    @JoinColumn(name = "discipulador_id")
    private Discipulador discipulador;

    @OneToMany(mappedBy = "discipulado")
    private List<Celula> celulas = new ArrayList<>();

    public Discipulado() {
    }

    public Discipulado(Long id) {
        this.id = id;
    }

    public Discipulado(String nome, Rede rede, Discipulador discipulador, List<Celula> celulas) {
        this.nome = nome;
        this.rede = rede;
        this.discipulador = discipulador;
        this.celulas = celulas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Discipulador getDiscipulador() {
        return discipulador;
    }

    public void setDiscipulador(Discipulador discipulador) {
        this.discipulador = discipulador;
    }

    public List<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(List<Celula> celulas) {
        this.celulas = celulas;
    }

    public Rede getRede() {
        return rede;
    }

    public void setRede(Rede rede) {
        this.rede = rede;
    }

    @Override
    public String toString() {
        return "Discipulado{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", rede=" + rede +
                ", discipulador=" + discipulador +
                ", celulas=" + celulas +
                '}';
    }
}
