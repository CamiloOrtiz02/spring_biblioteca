package com.egg.biblioteca.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    private String nombre;

    public Editorial(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
