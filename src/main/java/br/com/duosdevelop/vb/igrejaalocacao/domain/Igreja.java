package br.com.duosdevelop.vb.igrejaalocacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Igreja implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    public Igreja(Integer id, String nome, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Igreja igreja = (Igreja) o;
        return Objects.equals(id, igreja.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
