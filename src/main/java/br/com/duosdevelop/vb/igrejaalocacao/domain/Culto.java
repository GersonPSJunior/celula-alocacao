package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Culto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private DiasSemana dia;
    private Date horario;

    @ManyToOne
    @JoinColumn(name = "igreja_id")
    private Igreja igreja;

    public Culto() {
    }

    public Culto(Integer id, String nome, DiasSemana dia, Date horario, Igreja igreja) {
        this.id = id;
        this.nome = nome;
        this.dia = dia;
        this.horario = horario;
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

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Culto culto = (Culto) o;
        return Objects.equals(id, culto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
