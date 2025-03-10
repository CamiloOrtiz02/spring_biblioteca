package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Entity.Editorial;
import com.egg.biblioteca.Service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialService es;

    @GetMapping("/registrar") // /editorial/registrar
    public String mostrarFormEditorial() {
        return "editorial_form.html";
    }

    @GetMapping("/lista")
    public String listarEditoriales(Model model) {
        model.addAttribute("editoriales", es.listarEditoriales());
        return "editorial_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormModificarEditorial(@PathVariable("id") String id, Model model) {
        Editorial editorial = es.getOne(id);
        if (editorial == null) {
            return "redirect:/editorial/lista";
        }
        model.addAttribute("editorial", editorial);
        return "editorial_modificar.html";
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

    @PostMapping("/modificar/{id}")
    public String modificarEditorial(@PathVariable("id") String id, @RequestParam("nombre") String nombre, Model model) {
        if (nombre == null || nombre.isEmpty()) {
            // Mostrar error en Vista
            model.addAttribute("error", "El nombre de la editoria no puede ser vacio");
            return "redirect:/editorial/modificar/" + id;
        }
        try {
            es.modificarEditorial(id, nombre);
        } catch (Exception e) {
            // Mostrar error en Vista
            model.addAttribute("error", "Error al modificar la editorial");
            return "redirect:/editorial/modificar/" + id;
        }
        model.addAttribute("exito", "Editorial modificada con exito");
        return "redirect:/editorial/lista";
    }
}
