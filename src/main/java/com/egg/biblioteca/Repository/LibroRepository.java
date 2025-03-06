package com.egg.biblioteca.Repository;

import com.egg.biblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo") // SELECT * FROM Libro WHERE titulo = "Camilo"
    public Libro buscarPorTitulo(@Param("titulo") String titulo);

    Libro findByTitulo(String titulo); // SELECT * FROM Libro WHERE titulo = "Camilo"

}
