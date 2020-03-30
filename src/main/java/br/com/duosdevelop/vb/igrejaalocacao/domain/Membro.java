package br.com.duosdevelop.vb.igrejaalocacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Membro extends Pessoa{

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    private Boolean batizado;

    public Membro(String nome, Date nascimento, String cpf) {
        super(nome, nascimento, cpf);
    }

    public Membro(Integer id, String nome, Date nascimento, String cpf, Celula celula) {
        super(id, nome, nascimento, cpf);
        this.celula = celula;
    }

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }

    public Boolean getBatizado() {
        return batizado;
    }

    public void setBatizado(Boolean batizado) {
        this.batizado = batizado;
    }
}
