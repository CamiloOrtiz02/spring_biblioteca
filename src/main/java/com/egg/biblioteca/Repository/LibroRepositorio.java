package com.egg.biblioteca.Repository;

import com.egg.biblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    // Buscar Libro por Titulo
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String tituloRecibido);
}