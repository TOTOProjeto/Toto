package br.edu.iff.ccc.webdev.dto;

import jakarta.validation.constraints.NotBlank;

public class ParticaoDTO {

    private Integer id;

    @NotBlank(message = "O nome da partição é obrigatório")
    private String nome;

    private Integer diagramaId; 

    public ParticaoDTO() {
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

    public Integer getDiagramaId() {
        return diagramaId;
    }

    public void setDiagramaId(Integer diagramaId) {
        this.diagramaId = diagramaId;
    }
}