package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Celula implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Membro lider;
    private DiasSemana dia;
    private Date horario;
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "celula")
    private List<Membro> membros = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "discipulado_id")
    private Discipulado discipulado;

    public Celula() {
    }

    public Celula(Integer id, String nome, Membro lider, DiasSemana dia, Date horario, Endereco endereco, Discipulado discipulado) {
        this.id = id;
        this.nome = nome;
        this.lider = lider;
        this.dia = dia;
        this.horario = horario;
        this.endereco = endereco;
        this.discipulado = discipulado;
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

    public Membro getLider() {
        return lider;
    }

    public void setLider(Membro lider) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celula celula = (Celula) o;
        return Objects.equals(id, celula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
