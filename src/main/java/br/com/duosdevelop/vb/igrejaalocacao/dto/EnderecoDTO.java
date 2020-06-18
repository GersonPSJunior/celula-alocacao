package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Endereco;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class EnderecoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;
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
    @Size(min = 1)
    private Long cidade;

    @NotNull
    @Size(min = 1)
    private Long estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Endereco toDomain() {
        Endereco endereco = new Endereco(rua, numero, null, null, cep,
                new Cidade(cidade));
        if(complemento != null && !complemento.equals(""))
            endereco.setComplemento(complemento);
        if(bairro != null && !bairro.equals(""))
            endereco.setBairro(bairro);
        return endereco;
    }
}
