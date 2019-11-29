package br.com.duosdevelop.vb.igrejaalocacao.dto;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Celula;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Discipulado;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiscipuladoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Membro discipulador;
    private List<Celula> celulas = new ArrayList<>();

    public  DiscipuladoDTO(Discipulado discipulado){
        id = discipulado.getId();
        nome = discipulado.getNome();
        discipulador = discipulado.getDiscipulador();
        celulas = discipulado.getCelulas();
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

    public Membro getDiscipulador() {
        return discipulador;
    }

    public void setDiscipulador(Membro discipulador) {
        this.discipulador = discipulador;
    }

    public List<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(List<Celula> celulas) {
        this.celulas = celulas;
    }
}
