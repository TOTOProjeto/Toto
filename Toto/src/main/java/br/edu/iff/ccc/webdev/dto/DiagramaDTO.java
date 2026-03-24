package br.edu.iff.ccc.webdev.dto;

import jakarta.validation.constraints.NotBlank;

public class DiagramaDTO {

    private Integer id;

    @NotBlank(message = "O nome do diagrama é obrigatório")
    private String nome;

    private String descricao;

    private Long donoId; 
    
    private Long timeId; 

    public DiagramaDTO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDonoId() {
        return donoId;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }
}