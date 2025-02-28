package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Repository.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio ar;

    public void crearAutor(String nombre) {
        if (nombre == null && nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor no puede ser nulo o vacío.");
        }

        try {
            Autor autor = new Autor();
            autor.setNombre(nombre);
            ar.save(autor);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el autor: " + e.getMessage());
        }
    }
}
