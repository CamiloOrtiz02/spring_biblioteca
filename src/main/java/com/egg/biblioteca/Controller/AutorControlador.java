package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Service.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    @Autowired
    private AutorServicio as;

    private final Logger logger = Logger.getLogger(AutorControlador.class.getName());

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form";
    }

    @PostMapping("/registrarse")
    public String registrarAutor(@RequestParam(required = true) String nombre) {
        try {
            as.crearAutor(nombre);
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error en el controlador de POST('/registrar')", e);
            return "autor_form"; // En caso de surgir un error, quedarse en la pagina del Autor
        }
        return "index"; // Si todo sale Ok, redirigir al Index
    }
}
