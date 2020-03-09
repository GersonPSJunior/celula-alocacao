package br.com.duosdevelop.vb.igrejaalocacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private List<Integer> membros = new ArrayList<>();

    @NotNull
    private Integer discipulado;

    @NotNull
    private NewEnderecoDTO endereco;

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

    public List<Integer> getMembros() {
        return membros;
    }

    public void setMembros(List<Integer> membros) {
        this.membros = membros;
    }

    public Integer getDiscipulado() {
        return discipulado;
    }

    public void setDiscipulado(Integer discipulado) {
        this.discipulado = discipulado;
    }

    public NewEnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(NewEnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
