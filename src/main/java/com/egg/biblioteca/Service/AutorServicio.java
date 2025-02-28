package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Repository.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio ar;

    public void crearAutor(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor no puede ser nulo o vacío.");
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);
        ar.save(autor);
    }

    public Autor buscarPorId(Long id) {
        return ar.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el autor con ID: " + id));
    }

    public List<Autor> listarAutores() {
        return ar.findAll();
    }
}
