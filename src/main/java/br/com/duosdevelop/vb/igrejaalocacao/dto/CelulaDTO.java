package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Endereco;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;

import java.io.Serializable;
import java.util.Date;

public class CelulaDTO implements Serializable {

    private Integer id;
    private String nome;
    private String lider;
    private DiasSemana dia;
    private Date horario;
    private Endereco endereco;

    public CelulaDTO(Celula celula){
        id = celula.getId();
        nome = celula.getNome();
        lider = celula.getLider();
        dia = celula.getDia();
        horario = celula.getHorario();
        endereco = celula.getEndereco();
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
}
