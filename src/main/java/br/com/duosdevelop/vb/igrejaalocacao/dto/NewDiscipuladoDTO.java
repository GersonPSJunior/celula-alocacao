package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulador;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Rede;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class NewDiscipuladoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(max = 50, min = 3)
    private String nome;

    @NotNull
    @JsonProperty(value = "id_rede")
    private Long idRede;

    @NotNull
    @JsonProperty(value = "id_discipulado")
    private Long idDiscipulador;

    @NotNull
    @JsonProperty(value = "id_celula")
    private List<Long> idCelula;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdRede() {
        return idRede;
    }

    public void setIdRede(Long idRede) {
        this.idRede = idRede;
    }

    public Long getIdDiscipulador() {
        return idDiscipulador;
    }

    public void setIdDiscipulador(Long idDiscipulador) {
        this.idDiscipulador = idDiscipulador;
    }

    public List<Long> getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(List<Long> idCelula) {
        this.idCelula = idCelula;
    }

    public Discipulado toDomain() {
        new Discipulado(nome, new Rede(idRede), new Discipulador(idDiscipulador),
                idCelula.stream().map(Celula::new).collect(Collectors.toList()));
        return null;
    }
}
