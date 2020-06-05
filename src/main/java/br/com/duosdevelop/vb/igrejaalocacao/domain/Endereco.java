package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco extends EntityBase<Endereco> {

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String codPostal;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(String rua, String numero, String complemento, String bairro, String codPostal, Cidade cidade) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codPostal = codPostal;
        this.cidade = cidade;
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

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + getId() +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", codPostal='" + codPostal + '\'' +
                ", cidade=" + cidade +
                '}';
    }

    public void replaceValues(List<Endereco> enderecos) {
        enderecos.forEach(endereco -> {
            rua = endereco.getRua();
            numero = endereco.getNumero();
            complemento = endereco.getComplemento();
            bairro = endereco.getBairro();
            codPostal = endereco.getCodPostal();
            cidade = endereco.getCidade();
        });
    }
}
