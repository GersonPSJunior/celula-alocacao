package br.com.duosdevelop.vb.igrejaalocacao.domain;

import br.com.duosdevelop.vb.igrejaalocacao.domain.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Pessoa extends EntityBase<Pessoa> {
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;

    @Column(unique = true)
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefone = new HashSet<>();

    @OneToMany(mappedBy = "pessoa", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(String nome, LocalDate nascimento, String cpf) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + getId() +
                "nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", cpf='" + cpf + '\'' +
                ", telefone=" + telefone +
                ", enderecos=" + enderecos +
                '}';
    }

    public void replaceValues(Pessoa pessoaReplace) {
        nome = pessoaReplace.getNome();
        nascimento = pessoaReplace.getNascimento();
        cpf = pessoaReplace.getCpf();
        telefone = pessoaReplace.getTelefone();
        enderecos.forEach(endereco -> endereco.replaceValues(pessoaReplace.getEnderecos()));
    }
}
