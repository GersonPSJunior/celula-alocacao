package br.com.duosdevelop.vb.igrejaalocacao.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Rede implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @OneToOne
    private Membro obreiro;

    @ManyToOne
    @JoinColumn(name = "igreja_id")
    private Igreja igreja;

    @OneToMany(mappedBy = "rede")
    private List<Discipulado> discipulados = new ArrayList<>();

    public Rede() {
    }

    public Rede(Integer id, String nome, Membro obreiro, Igreja igreja) {
        this.id = id;
        this.nome = nome;
        this.obreiro = obreiro;
        this.igreja = igreja;
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

    public Membro getObreiro() {
        return obreiro;
    }

    public void setObreiro(Membro obreiro) {
        this.obreiro = obreiro;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public List<Discipulado> getDiscipulados() {
        return discipulados;
    }

    public void setDiscipulados(List<Discipulado> discipulados) {
        this.discipulados = discipulados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rede rede = (Rede) o;
        return Objects.equals(id, rede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
