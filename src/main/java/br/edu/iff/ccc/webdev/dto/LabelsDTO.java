package br.edu.iff.ccc.webdev.dto;

import jakarta.validation.constraints.NotBlank;

public class LabelsDTO {

    private Integer id;

    @NotBlank(message = "O nome da label é obrigatório")
    private String nome;

    private String descricao;

    public LabelsDTO() {
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
}