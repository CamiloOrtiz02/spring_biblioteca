package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Repository.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio er;

    public void crearEditorial(String nombre) {
        if (nombre == null && nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial no puede ser nulo o vacío.");
        }
        try {
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            er.save(editorial);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la editorial: " + e.getMessage());
        }
    }
}
