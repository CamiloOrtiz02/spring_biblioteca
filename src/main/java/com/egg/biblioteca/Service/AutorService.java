package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository ar;

    @Transactional
    public void crearAutor(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor no puede estar vacío.");
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);
        ar.save(autor);
    }

    public Autor buscarAutorPorId(String id) {
        return ar.findById(id).orElse(null);
    }

    public List<Autor> listarAutores() {
        return ar.findAll();
    }
}
