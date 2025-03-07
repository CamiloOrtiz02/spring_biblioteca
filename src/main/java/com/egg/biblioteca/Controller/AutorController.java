package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService as;

    @GetMapping("/registrar")
    public String mostrarFormAutor() {
        return "autor_form.html";
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
}
