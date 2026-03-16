package br.edu.iff.ccc.webdev.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TarefaDTO {

    private Long id;

    @NotBlank(message = "O nome da tarefa não pode estar em branco")
    @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private LocalDate dataCriacao;

    @FutureOrPresent(message = "O prazo deve ser uma data atual ou futura")
    private LocalDate prazo;

    @Min(value = 1, message = "A prioridade mínima é 1")
    @Max(value = 5, message = "A prioridade máxima é 5")
    private Integer prioridade; 

    private Long usuarioId;
    private Long equipeId;
    private Integer particaoId; 
    private List<Integer> labelsIds;

    public Integer getParticaoId() { return particaoId; }
    public void setParticaoId(Integer particaoId) { this.particaoId = particaoId; }
    public List<Integer> getLabelsIds() { return labelsIds; }
    public void setLabelsIds(List<Integer> labelsIds) { this.labelsIds = labelsIds; }
    
    public TarefaDTO() {
    }

    public TarefaDTO(Long id, String nome, String descricao, LocalDate dataCriacao, LocalDate prazo, Integer prioridade, Long usuarioId, Long equipeId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.usuarioId = usuarioId;
        this.equipeId = equipeId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }
}