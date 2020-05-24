package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Culto extends EntityBase<Culto> {

    private String nome;
    private DiasSemana dia;
    @JsonFormat(pattern = "HH:mm")
    private Date horario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "igreja_id")
    private Igreja igreja;

    public Culto() {
    }

    public Culto(String nome, DiasSemana dia, Date horario, Igreja igreja) {
        this.nome = nome;
        this.dia = dia;
        this.horario = horario;
        this.igreja = igreja;
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
    public String toString() {
        return "Culto{" +
                "id=" + getId() +
                "nome='" + nome + '\'' +
                ", dia=" + dia +
                ", horario=" + horario +
                ", igreja=" + igreja +
                '}';
    }
}
