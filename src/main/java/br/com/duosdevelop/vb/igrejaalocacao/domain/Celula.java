package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Celula extends EntityBase<Celula> {

    private String nome;
    private String lider;
    private DiasSemana dia;
    @JsonFormat(pattern = "HH:mm")
    private Date horario;
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "celula")
    private List<Membro> membros = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "discipulado_id")
    private Discipulado discipulado;

    public Celula() {
    }

    public Celula(Long id) {
        this.id = id;
    }

    public Celula(String nome, String lider, DiasSemana dia, Date horario, Endereco endereco, Discipulado discipulado) {
        this.nome = nome;
        this.lider = lider;
        this.dia = dia;
        this.horario = horario;
        this.endereco = endereco;
        this.discipulado = discipulado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public DiasSemana getDia() {
        return dia;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Discipulado getDiscipulado() {
        return discipulado;
    }

    public void setDiscipulado(Discipulado discipulado) {
        this.discipulado = discipulado;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    @Override
    public String toString() {
        return "Celula{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", lider='" + lider + '\'' +
                ", dia=" + dia +
                ", horario=" + horario +
                ", endereco=" + endereco +
                ", membros=" + membros +
                ", discipulado=" + discipulado +
                '}';
    }
}
