package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Entity.Libro;
import com.egg.biblioteca.Repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LibroService {
    @Autowired
    private LibroRepository lr;

    @Autowired
    private AutorService as;

    @Autowired
    private EditorialService es;

    public Libro buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        }
        return lr.findByTitulo(titulo);
    }

    @Transactional
    public void crearLibro(Long isbn, String titulo, int ejemplares, String idAutor, String idEditorial) {
        // Validaciones básicas
        if (isbn == null || titulo == null || titulo.trim().isEmpty() || ejemplares <= 0) {
            throw new IllegalArgumentException("Los datos del libro no son válidos.");
        }

        // Buscar la editorial
        Editorial editorial = es.buscarEditorialPorId(idEditorial);

        // Buscar el autor
        Autor autor = as.buscarAutorPorId(idAutor);

        // Crear y asignar valores
        Libro l = new Libro();
        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setEjemplares(ejemplares);
        l.setAlta(new Date());
        l.setEditorial(editorial);
        l.setAutor(autor);

        // Guardar libro
        lr.save(l);
    }
}
