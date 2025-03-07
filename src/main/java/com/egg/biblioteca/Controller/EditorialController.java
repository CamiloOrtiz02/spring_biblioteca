package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialService es;

    @GetMapping("/registrar")
    public String mostrarFormEditorial() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registrarEditorial(@RequestParam("nombre") String nombre, Model model) {
        if (nombre == null || nombre.isEmpty()) {
            // Mostrar error en Vista
            model.addAttribute("error", "El nombre de la editoria no puede ser vacio");
            return "redirect:/editorial/registrar";
        }
        try {
            es.crearEditorial(nombre);
        } catch (Exception e) {
            // Mostrar error en Vista
            model.addAttribute("error", "Error al crear la editorial");
            return "redirect:/editorial/registrar";
        }
        model.addAttribute("exito", "Editorial creada con exito");
        return "redirect:/";
    }
}
