package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Igreja extends EntityBase<Igreja> {
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "igreja")
    private List<Culto> cultos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "igreja")
    private List<Rede> redes = new ArrayList<>();

    public Igreja() {
    }

    public Igreja(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Culto> getCultos() {
        return cultos;
    }

    public void setCultos(List<Culto> cultos) {
        this.cultos = cultos;
    }

    public List<Rede> getRedes() {
        return redes;
    }

    public void setRedes(List<Rede> redes) {
        this.redes = redes;
    }

    @Override
    public String toString() {
        return "Igreja{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", cultos=" + cultos +
                ", redes=" + redes +
                '}';
    }
}
