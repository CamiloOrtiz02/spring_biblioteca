package com.egg.biblioteca.Controller;

import com.egg.biblioteca.Entity.Usuario;
import com.egg.biblioteca.Service.UsuarioServicio;
import com.egg.biblioteca.enums.Rol;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {
    @Autowired
    private UsuarioServicio us;
    @GetMapping({"/", "/index"}) // Permite renderizar el Template mediante la URL / o /index utilizando el formato {"map1","map2", ...}
    public String index() {
        return "inicio.html";
    }

    @GetMapping("/registrar")
    public String mostrarFormRegistrar() {
        return "registro.html";
    }

    @GetMapping("/login")
    public String mostrarFormLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        Usuario login = (Usuario) session.getAttribute("usuariosession"); // Obtener el usuario de la sesión
        if (login.getRol().toString().equals(Rol.ADMIN.toString())) {
            return "redirect:/admin/dashboard";
        }
        return "inicio.html";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, Model model) {
        try {
            us.crearUsuario(nombre, email, password, password2);
            model.addAttribute("exito", "Usuario creado con exito");
            return "inicio.html";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro.html";
        }
    }
}
