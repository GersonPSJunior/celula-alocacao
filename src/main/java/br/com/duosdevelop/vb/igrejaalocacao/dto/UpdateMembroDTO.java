package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Pessoa;
import br.com.duosdevelop.vb.igrejaalocacao.services.utils.DateUtil;
import br.com.duosdevelop.vb.igrejaalocacao.services.validation.CPFExist;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class UpdateMembroDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6)
    private String senha;

    @NotNull
    @NotBlank
    private String telefone1;
    private String telefone2;
    private String telefone3;

    @NotNull
    private Boolean batizado;

    @NotNull
    private Boolean ativo;

    @NotNull
    private EnderecoDTO endereco;

    public UpdateMembroDTO() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Boolean getBatizado() {
        return batizado;
    }

    public void setBatizado(Boolean batizado) {
        this.batizado = batizado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Membro toDomain() throws Exception {
        Pessoa pessoa = new Pessoa(nome, LocalDate.parse(nascimento, DateUtil.MEDIUM_DATE_FORMATTER), cpf, email, senha);
        pessoa.setEnderecos(Arrays.asList(endereco.toDomain()));
        pessoa.getTelefone().add(telefone1);
        if(telefone2 != null && !"".equals(telefone2))
            pessoa.getTelefone().add(telefone2);
        if(telefone3 != null && !"".equals(telefone3))
            pessoa.getTelefone().add(telefone3);
        return new Membro(pessoa, batizado, ativo);
    }
}
