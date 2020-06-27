package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Pessoa extends EntityBase<Pessoa> {
    private String nome;

    private String nascimento;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pessoa_permissao", joinColumns = @JoinColumn(name = "id_pessoa")
            , inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    @JsonIgnore
    private List<Permissao> permissoes;

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefone = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(String nome, String nascimento, String cpf, String email, String senha) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public Set<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(Set<String> telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void replaceValues(Pessoa pessoaReplace) {
        nome = pessoaReplace.getNome();
        nascimento = pessoaReplace.getNascimento();
        cpf = pessoaReplace.getCpf();
        telefone = pessoaReplace.getTelefone();
        enderecos.forEach(endereco -> endereco.replaceValues(pessoaReplace.getEnderecos()));
    }
}
