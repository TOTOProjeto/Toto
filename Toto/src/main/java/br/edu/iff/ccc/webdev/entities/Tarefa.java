package br.edu.iff.ccc.webdev.entities;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Tarefa implements Serializable {
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate prazo;
    private Integer prioridade; 

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

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getTitulo() { 
        return titulo; 
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }

    public LocalDate getDataCriacao() { 
        return dataCriacao; 
    }
    public void setDataCriacao(LocalDate dataCriacao) { 
        this.dataCriacao = dataCriacao; 
    }

    public LocalDate getPrazo() { 
        return prazo; 
    }
    public void setPrazo(LocalDate prazo) { 
        this.prazo = prazo; 
    }

    public Integer getPrioridade() { 
        return prioridade; 
    }
    public void setPrioridade(Integer prioridade) { 
        this.prioridade = prioridade; 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((prazo == null) ? 0 : prazo.hashCode());
        result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarefa other = (Tarefa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null)
                return false;
        } else if (!dataCriacao.equals(other.dataCriacao))
            return false;
        if (prazo == null) {
            if (other.prazo != null)
                return false;
        } else if (!prazo.equals(other.prazo))
            return false;
        if (prioridade == null) {
            if (other.prioridade != null)
                return false;
        } else if (!prioridade.equals(other.prioridade))
            return false;
        return true;
    }
    
}