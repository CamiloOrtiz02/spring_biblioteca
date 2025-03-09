package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Repository.EditorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository er;

    @Transactional
    public void crearEditorial(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial no puede estar vacío.");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        er.save(editorial);
    }

    @Transactional
    public void modificarEditorial(String id, String nombre) {
        Editorial editorial = er.getOne(id);
        if (editorial != null) {
            editorial.setNombre(nombre);
            er.save(editorial);
        }
    }

    public Editorial buscarEditorialPorId(String id) {
        return er.findById(id).orElse(null);
    }

    public List<Editorial> listarEditoriales() {
        return er.findAll();
    }

    public Editorial getOne(String id) {
        return er.getReferenceById(id);
    }
}
