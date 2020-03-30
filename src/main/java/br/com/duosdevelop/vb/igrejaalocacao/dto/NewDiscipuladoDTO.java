package br.com.duosdevelop.vb.igrejaalocacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class NewDiscipuladoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(max = 50, min = 3)
    private String nome;

    @NotNull
    @JsonProperty(value = "id_rede")
    private Integer idRede;

    @NotNull
    @JsonProperty(value = "id_discipulado")
    private Integer idDiscipulador;

    @JsonProperty(value = "id_celula")
    private List<Integer> idCelula;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdRede() {
        return idRede;
    }

    public void setIdRede(Integer idRede) {
        this.idRede = idRede;
    }

    public Integer getIdDiscipulador() {
        return idDiscipulador;
    }

    public void setIdDiscipulador(Integer idDiscipulador) {
        this.idDiscipulador = idDiscipulador;
    }

    public List<Integer> getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(List<Integer> idCelula) {
        this.idCelula = idCelula;
    }
}
