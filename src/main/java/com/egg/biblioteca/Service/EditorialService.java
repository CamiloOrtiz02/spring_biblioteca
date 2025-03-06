package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository er;

    public void crearEditorial(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial no puede estar vacío.");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        er.save(editorial);
    }
}
