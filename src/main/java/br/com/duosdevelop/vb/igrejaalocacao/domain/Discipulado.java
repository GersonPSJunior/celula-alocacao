package br.com.duosdevelop.vb.igrejaalocacao.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Discipulado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    public Discipulado(Integer id, String nome, Rede rede) {
        this.id = id;
        this.nome = nome;
        this.rede = rede;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipulado that = (Discipulado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
