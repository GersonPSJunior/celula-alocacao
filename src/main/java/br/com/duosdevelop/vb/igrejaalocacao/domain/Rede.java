package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rede extends EntityBase<Rede> {
    private String nome;

    @OneToOne
    private Membro obreiro;

    @ManyToOne
    @JoinColumn(name = "igreja_id")
    private Igreja igreja;

    @JsonIgnore
    @OneToMany(mappedBy = "rede")
    private List<Discipulado> discipulados = new ArrayList<>();

    public Rede() {
    }

    public Rede(Long id){
        this.id = id;
    }
    public Rede(String nome, Membro obreiro, Igreja igreja) {
        this.nome = nome;
        this.obreiro = obreiro;
        this.igreja = igreja;
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
    public String toString() {
        return "Rede{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", obreiro=" + obreiro +
                ", igreja=" + igreja +
                ", discipulados=" + discipulados +
                '}';
    }
}
