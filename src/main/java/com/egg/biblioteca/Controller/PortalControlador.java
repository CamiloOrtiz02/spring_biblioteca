package com.egg.biblioteca.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping({"/", "/index"}) // Permite renderizar el Template mediante la URL / o /index utilizando el formato {"map1","map2", ...}
    public String index() {
        return "index";
    }
}
