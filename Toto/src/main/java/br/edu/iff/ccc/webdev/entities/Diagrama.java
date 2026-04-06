package br.edu.iff.ccc.webdev.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Diagrama implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String descricao;

    @ManyToOne
    private Usuario dono;

    @ManyToOne
    private Equipe time;

    @OneToMany(mappedBy = "diagrama", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Particao> particoes = new ArrayList<>();

    public Diagrama() {
    }

    public Diagrama(int id, String nome, String descricao, Usuario dono, Equipe time) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dono = dono;
        this.time = time;
    }

    public void adicionarParticao(Particao particao) {
        this.particoes.add(particao);
    }

    public void adicionarTime(Equipe time) {
        this.time = time;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Equipe getTime() {
        return time;
    }

    public void setTime(Equipe time) {
        this.time = time;
    }

    public List<Particao> getParticoes() {
        return particoes;
    }

    public void setParticoes(List<Particao> particoes) {
        this.particoes = particoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((dono == null) ? 0 : dono.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((particoes == null) ? 0 : particoes.hashCode());
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
        Diagrama other = (Diagrama) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (dono == null) {
            if (other.dono != null)
                return false;
        } else if (!dono.equals(other.dono))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (particoes == null) {
            if (other.particoes != null)
                return false;
        } else if (!particoes.equals(other.particoes))
            return false;
        return true;
    }

}