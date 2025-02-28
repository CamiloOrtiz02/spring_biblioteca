package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Repository.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio er;

    public void crearEditorial(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial no puede ser nulo o vacío.");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        er.save(editorial);
    }


    public Editorial buscarPorId(Long idEditorial) {
        return er.findById(idEditorial)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la editorial con ID: " + idEditorial));
    }

    public List<Editorial> listarEditoriales() {
        return er.findAll();
    }
}
