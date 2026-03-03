package br.edu.iff.ccc.webdev.dto;

import java.time.LocalDate;

public class TarefaDTO {

    public Long id;
    public String nome;
    public String descricao;
    public LocalDate dataCriacao;
    public LocalDate prazo;
    public Integer prioridade; 

    public TarefaDTO() {
    }

    public TarefaDTO(LocalDate dataCriacao, String descricao, Long id, String nome, LocalDate prazo, Integer prioridade) {
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
        this.prazo = prazo;
        this.prioridade = prioridade;
    }

    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    
}
