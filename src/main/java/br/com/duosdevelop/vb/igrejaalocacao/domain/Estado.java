package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado extends EntityBase<Estado> {

    private String nome;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.REMOVE)
    private List<Cidade> cidades = new ArrayList<>();

    public Estado() {
    }

    public Estado(Long id) {
        this.id = id;
    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", cidades=" + cidades +
                '}';
    }
}
