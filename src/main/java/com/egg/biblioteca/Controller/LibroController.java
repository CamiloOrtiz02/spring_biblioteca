package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Entity.Libro;
import com.egg.biblioteca.Service.AutorService;
import com.egg.biblioteca.Service.EditorialService;
import com.egg.biblioteca.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/lista")
    public String listarLibros(Model model) {
        model.addAttribute("libros", ls.listarLibros());
        return "libro_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormModificarLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = ls.getOne(id);
        if (libro == null) {
            return "redirect:/libro/lista";
        }
        // Lista de Autores
        List<Autor> listaAutor = as.listarAutores();
        // Lista de Editoriales
        List<Editorial> listaEditorial = es.listarEditoriales();

        model.addAttribute("autores", listaAutor);
        model.addAttribute("editoriales", listaEditorial);
        model.addAttribute("libro", libro);
        return "libro_modificar.html";
    }

    @PostMapping("/registro")
    public String registrarLibro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                                 @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
                                 @RequestParam String idEditorial, RedirectAttributes ra) {

        // RedirectAttributes permite redireccionar y mandar informacion al HTML mediante la URL
        try {
            ls.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            ra.addFlashAttribute("exito", "Libro creado con éxito");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al crear el libro");
        }
        return "redirect:/libro/registrar";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificarLibro(@PathVariable("isbn") Long isbn,
                                 @RequestParam("titulo") String titulo,
                                 @RequestParam("ejemplares") Integer ejemplares,
                                 @RequestParam("idAutor") String idAutor,
                                 @RequestParam("idEditorial") String idEditorial,
                                 Model model) {
        if (titulo == null || titulo.isEmpty()) {
            // Mostrar error en Vista
            model.addAttribute("error", "El título no puede ser vacío");
            return "redirect:/libro/modificar/" + isbn;
        }

        try {
            // Aquí se puede agregar la lógica para modificar el libro
            ls.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
        } catch (Exception e) {
            // Mostrar error en Vista
            model.addAttribute("error", "Error al modificar el libro");
            return "redirect:/libro/modificar/" + isbn;
        }

        model.addAttribute("exito", "Libro modificado con éxito");
        return "redirect:/libro/lista";
    }

}
