package com.egg.biblioteca.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long isbn;

    @Column(unique = true)
    private String titulo;
    private int ejemplares;

    @Temporal(TemporalType.DATE)
    private Date alta;

    // Relaciones con otras Entidades
    @ManyToOne // Muchos libros pueden tener una editorial
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
