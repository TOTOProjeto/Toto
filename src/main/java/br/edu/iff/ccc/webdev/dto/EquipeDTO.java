package br.edu.iff.ccc.webdev.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class EquipeDTO {

    private Long id;
    @NotBlank(message = "Nome da equipe é obrigatório")
    private String nome;
    private String descricao;
    private Long responsavelId; 
    private List<Long> membrosIds; 

    public EquipeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public List<Long> getMembrosIds() {
        return membrosIds;
    }

    public void setMembrosIds(List<Long> membrosIds) {
        this.membrosIds = membrosIds;
    }

}
