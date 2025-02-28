package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Service.AutorServicio;
import com.egg.biblioteca.Service.EditorialServicio;
import com.egg.biblioteca.Service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio ls;
    @Autowired
    private AutorServicio as;
    @Autowired
    private EditorialServicio es;

    private final Logger logger = Logger.getLogger(LibroControlador.class.getName());

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        // Obtener autores
        model.addAttribute("autores", as.listarAutores());
        // Obtener editoriales
        model.addAttribute("editoriales", es.listarEditoriales());

        return "libro_form";
    }

    @PostMapping("/registrarse")
    public String registrarLibro(
            @RequestParam(required = true) String titulo,
            @RequestParam(required = false) int ejemplares,
            @RequestParam(required = false) Long idAutor,
            @RequestParam(required = false) Long idEditorial
    ) {
        try {
            ls.crearLibro(titulo, ejemplares, idAutor, idEditorial);
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error en el controlador de POST('/registrar')", e);
            return "libro_form";
        }
        return "index";
    }
}
