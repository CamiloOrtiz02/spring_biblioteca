package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Service.AutorService;
import com.egg.biblioteca.Service.EditorialService;
import com.egg.biblioteca.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/libro")
public class LibroController {
    @Autowired
    private AutorService as;

    @Autowired
    private EditorialService es;

    @Autowired
    private LibroService ls;

    @GetMapping("/registrar")
    public String mostrarFormLibro(Model model) {
        // Obtenemos lista de Autores
        List<Autor> listaAutor = as.listarAutores();

        // Obtenemos lista de Editoriales
        List<Editorial> listaEditorial = es.listarEditoriales();

        model.addAttribute("autores", listaAutor);
        model.addAttribute("editoriales", listaEditorial);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registrarLibro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                                 @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
                                 @RequestParam String idEditorial, Model model) {

        try {
            ls.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear el libro");
            return "redirect:/libro/registrar";
        }
        return "libro_form.html";
    }

}
