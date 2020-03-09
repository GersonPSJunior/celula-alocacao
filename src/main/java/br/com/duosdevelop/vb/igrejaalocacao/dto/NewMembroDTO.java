package br.com.duosdevelop.vb.igrejaalocacao.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewMembroDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewMembroDTO() {
    }

    @NotNull
    @Size(min = 5, max = 120)
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String nascimento;

    @NotNull
    @CPF
    @NotBlank
    private String cpf;

    @NotNull
    private Integer celula;

    @NotNull
    @NotBlank
    private String telefone1;
    private String telefone2;
    private String telefone3;

    @NotNull
    private NewEnderecoDTO endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public NewEnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(NewEnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Integer getCelula() {
        return celula;
    }

    public void setCelula(Integer celula) {
        this.celula = celula;
    }
}
