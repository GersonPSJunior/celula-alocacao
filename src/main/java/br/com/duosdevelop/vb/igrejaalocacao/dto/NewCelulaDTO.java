package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.services.utils.DateUtil;

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
    private String nome;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 120)
    private String lider;

    @NotNull
    private Integer dia;

    @NotNull
    @NotBlank
    private String horario;

    @NotNull
    private List<Long> membros = new ArrayList<>();

    @NotNull
    private Long discipulado;

    @NotNull
    private EnderecoDTO endereco;

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

    public List<Long> getMembros() {
        return membros;
    }

    public void setMembros(List<Long> membros) {
        this.membros = membros;
    }

    public Long getDiscipulado() {
        return discipulado;
    }

    public void setDiscipulado(Long discipulado) {
        this.discipulado = discipulado;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Celula toDomain() throws Exception {
        Celula celula = new Celula(nome, lider, DiasSemana.toEnum(dia), DateUtil.toTime(horario), endereco.toDomain());
        celula.setMembros(membros.stream().map(Membro::new).collect(Collectors.toList()));
        return celula;
    }
}
