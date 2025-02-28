package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Service.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicio es;
    private final Logger logger = Logger.getLogger(EditorialControlador.class.getName());

    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_form";
    }

    @PostMapping("/registrarse")
    public String registrarEditorial(@RequestParam(required = true) String nombre) {
        try {
            es.crearEditorial(nombre);
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error en el controlador de POST('/registrar')", e);
            return "editorial_form"; // En caso de surgir un error, quedarse en la pagina del Editorial
        }
        return "index"; // Si todo sale Ok, redirigir al Index
    }
}
