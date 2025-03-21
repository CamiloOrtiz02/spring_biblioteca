package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Entity.Autor;
import com.egg.biblioteca.Service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService as;

    @GetMapping("/registrar") // /autor/registrar
    public String mostrarFormAutor() {
        return "autor_form.html";
    }

    @GetMapping("/lista") // /autor/lista
    public String listarAutores(Model model) {
        model.addAttribute("autores", as.listarAutores());
        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormModificarAutor(@PathVariable("id") String id, Model model) {
        Autor autor = as.getOne(id);
        if (autor == null) {
            return "redirect:/autor/lista";
        }
        model.addAttribute("autor", autor);
        return "autor_modificar.html";
    }


    @PostMapping("/registro")
    public String registrarAutor(@RequestParam("nombre") String nombreAutor, Model model) {
        if (nombreAutor == null || nombreAutor.isEmpty()) {
            // Mostrar error en Vista
            model.addAttribute("error", "El nombre no puede ser vacio");
            return "redirect:/autor/registrar";
        }
        try {
            as.crearAutor(nombreAutor);
        } catch (Exception e) {
            // Mostrar error en Vista
            model.addAttribute("error", "Error al crear el autor");
            return "redirect:/autor/registrar";
        }
        model.addAttribute("exito", "Autor creado con exito");
        return "redirect:/";
    }

    @PostMapping("/modificar/{id}")
    public String modificarAutor(@PathVariable("id") String id, @RequestParam("nombre") String nombreAutor, Model model) {
        if (nombreAutor == null || nombreAutor.isEmpty()) {
            // Mostrar error en Vista
            model.addAttribute("error", "El nombre no puede ser vacio");
            return "redirect:/autor/modificar/" + id;
        }
        try {
            as.modificarAutor(id, nombreAutor);
        } catch (Exception e) {
            // Mostrar error en Vista
            model.addAttribute("error", "Error al modificar el autor");
            return "redirect:/autor/modificar/" + id;
        }
        model.addAttribute("exito", "Autor modificado con exito");
        return "redirect:/autor/lista";
    }
}
