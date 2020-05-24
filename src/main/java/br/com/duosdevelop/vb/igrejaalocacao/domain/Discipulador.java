package br.com.duosdevelop.vb.igrejaalocacao.domain;


import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Discipulador extends EntityBase<Discipulador> {

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Discipulador() {
    }

    public Discipulador(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Discipulador{" +
                "id=" + getId() +
                "pessoa=" + pessoa +
                '}';
    }
}
