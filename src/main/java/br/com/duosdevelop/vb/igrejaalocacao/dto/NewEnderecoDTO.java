package br.com.duosdevelop.vb.igrejaalocacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewEnderecoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewEnderecoDTO() {
    }

    @NotNull
    @NotBlank
    private String rua;
    @NotNull
    @NotBlank
    private String numero;
    @NotNull
    @NotBlank
    private String cep;
    private String complemento;
    private String bairro;

    @NotNull
    private Integer cidade;

    private Integer estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCidade() {
        return cidade;
    }

    public void setCidade(Integer cidade) {
        this.cidade = cidade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
