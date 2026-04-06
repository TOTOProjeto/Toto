package br.edu.iff.ccc.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tarefas_table")
public class Tarefa implements Serializable {
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O título é obrigatório e não pode estar vazio")
    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_criacao", updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @FutureOrPresent(message = "O prazo não pode ser uma data passada")
    private LocalDate prazo;

    private Integer prioridade;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = true)
    private Equipe equipe;

    // NOVOS RELACIONAMENTOS BASEADOS NO DIAGRAMA:

    // Relação com Particao (Status)
    @ManyToOne
    @JoinColumn(name = "particao_id")
    private Particao status;

    // Relação com Labels (Marcas do diagrama)
    @ManyToMany
    @JoinTable(
        name = "tarefa_labels",
        joinColumns = @JoinColumn(name = "tarefa_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Labels> marcas = new ArrayList<>();

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, LocalDate prazo, Integer prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.prazo = prazo;
        this.prioridade = prioridade;
    }

    public void adicionarMarca(Labels label) {
        this.marcas.add(label);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public Equipe getEquipe() { return equipe; }
    public void setEquipe(Equipe equipe) { this.equipe = equipe; }
    public Particao getStatus() { return status; }
    public void setStatus(Particao status) { this.status = status; }
    public List<Labels> getMarcas() { return marcas; }
    public void setMarcas(List<Labels> marcas) { this.marcas = marcas; }
}