package br.edu.iff.ccc.webdev.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Particao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    private String nome;

    @ManyToOne
    private Diagrama diagrama;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> atividades = new ArrayList<>();

    public Particao() {
    }

    public Particao(int id, String nome, Diagrama diagrama) {
        this.id = id;
        this.nome = nome;
        this.diagrama = diagrama;
    }

    public void adicionarAtividade(Tarefa tarefa) {
        this.atividades.add(tarefa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Diagrama getDiagrama() {
        return diagrama;
    }

    public void setDiagrama(Diagrama diagrama) {
        this.diagrama = diagrama;
    }

    public List<Tarefa> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Tarefa> atividades) {
        this.atividades = atividades;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((diagrama == null) ? 0 : diagrama.hashCode());
        result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
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
        Particao other = (Particao) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (diagrama == null) {
            if (other.diagrama != null)
                return false;
        } else if (!diagrama.equals(other.diagrama))
            return false;
        if (atividades == null) {
            if (other.atividades != null)
                return false;
        } else if (!atividades.equals(other.atividades))
            return false;
        return true;
    }

}