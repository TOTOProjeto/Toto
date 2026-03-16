package br.edu.iff.ccc.webdev.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table(name = "tbl_equipe")
public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da equipe é obrigatório")
    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;
   
    @OneToMany(mappedBy = "equipe")
    private List<Tarefa> tarefas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "equipe_membros",
        joinColumns = @JoinColumn(name = "equipe_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> membros = new ArrayList<>();
    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }
    
    public void adicionarMembro(Usuario usuario) {
        this.membros.add(usuario);
    }

    public Equipe() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public List<Tarefa> getTarefas() { return tarefas; }
    public void setTarefas(List<Tarefa> tarefas) { this.tarefas = tarefas; }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}