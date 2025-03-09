package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Entity.Libro;
import com.egg.biblioteca.Repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository lr;

    @Autowired
    private AutorService as;

    @Autowired
    private EditorialService es;

    public List<Libro> listarLibros() { return lr.findAll(); }

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

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) {
        // Validaciones básicas
        if (titulo == null || titulo.trim().isEmpty() || ejemplares == null || ejemplares <= 0) {
            throw new IllegalArgumentException("Los datos del libro no son válidos.");
        }

        // Buscar la editorial
        Editorial editorial = es.getOne(idEditorial);

        // Buscar el autor
        Autor autor = as.getOne(idAutor);

        // Actualizar libro
        Libro l = lr.getOne(isbn);
        l.setTitulo(titulo);
        l.setEjemplares(ejemplares);
        l.setEditorial(editorial);
        l.setAutor(autor);
    }

    public Libro getOne(Long id) {
        return lr.getReferenceById(id);
    }
}
