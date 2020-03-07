package br.com.duosdevelop.vb.igrejaalocacao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Membro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date nascimento;
    private String cpf;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefone = new HashSet<>();

    @OneToMany(mappedBy = "membro")
    private List<Endereco> enderecos = new ArrayList<>();

    public Membro() {
    }

    public Membro(String nome, Date nascimento, String cpf) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }

    public Membro(Integer id, String nome, Date nascimento, String cpf, Celula celula) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.celula = celula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
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

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membro membro = (Membro) o;
        return Objects.equals(id, membro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
