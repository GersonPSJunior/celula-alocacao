package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends EntityBase<Cidade> {

    private String name;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Long id) {
        this.id = id;
    }

    public Cidade(String name, Estado estado) {
        this.name = name;
        this.estado = estado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id='" + getId() +
                "name='" + name + '\'' +
                ", estado=" + estado +
                '}';
    }
}
