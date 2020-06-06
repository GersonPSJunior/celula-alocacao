package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.services.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewCelulaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewCelulaDTO() {
    }

    @NotNull
    @NotBlank
    @Size(min = 3, max = 75)
    @ApiModelProperty(notes = "Deve colocar o nome da célula", required = true)
    public String nome;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 120)
    @ApiModelProperty(notes = "Nome do lider da célula", required = true)
    public String lider;

    @NotNull
    @ApiModelProperty(notes = "Dia da semana enum de 1 à 7", required = true)
    public Integer dia;

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "Deve colocar o horário da célula", required = true, example = "00:00")
    public String horario;

    @NotNull
    public EnderecoDTO endereco;

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

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Celula toDomain() throws Exception {
        Celula celula = new Celula(nome, lider, DiasSemana.toEnum(dia), DateUtil.toTime(horario), endereco.toDomain());
        return celula;
    }
}
