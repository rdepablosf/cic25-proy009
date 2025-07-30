package es.cic.curso25.proy009.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "arboles")
public class Arbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arbol")
    private Long id;

    @Column(name = "especie")
    private String especie;

    @Column(name = "altura")
    private double altura;

    @OneToMany(mappedBy = "arbol", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rama> ramas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public List<Rama> getRamas() {
        return ramas;
    }

    public void setRamas(List<Rama> ramas) {
        this.ramas = ramas;
    }

    public void addRama(Rama rama) {
        ramas.add(rama);
        rama.setArbol(this);
    }

    public void removeRama(Rama rama) {
        ramas.remove(rama);
        rama.setArbol(null);
    }

    @Override
    public String toString() {
        return "Arbol [id=" + id + ", especie=" + especie + ", altura=" + altura + "]";
    }

}
