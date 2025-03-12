package com.egg.biblioteca.Service;

import com.egg.biblioteca.Repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.egg.biblioteca.Entity.Usuario;
import com.egg.biblioteca.enums.Rol;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepository ur;

    public void crearUsuario(String nombre, String email, String password, String password2) {
        validarUsuario(nombre, email, password, password2);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER); // Valor por defecto

        ur.save(usuario);
    }

    private void validarUsuario(String nombre, String email, String password, String password2) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía y debe tener mas de 5 caracteres.");
        }
        if (!password.equals(password2)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
    }

    /*
        Punto de entrada para la autenticación de usuarios
        Sin esta funcion Spring Security no sabe como cargar el usuario
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = ur.findByEmail(email);
        if (usuario != null) {
            // Asignar los permisos
            List<GrantedAuthority> grants = new ArrayList<>(); // Lista de permisos
            GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); // Permiso ROLE_USER || ROLE_ADMIN
            grants.add(ga); // Agregar permiso

            // Guardar el usuario en la sesión
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); // Obtener la petición actual
            HttpSession session = attr.getRequest().getSession(); // Obtener la sesión
            session.setAttribute("usuariosession", usuario); // Guardar el usuario en la sesión

            return new User(usuario.getEmail(), usuario.getPassword(), grants); // Aplicar los permiso del usuario
        }
        throw new UsernameNotFoundException("El usuario no existe.");
    }
}
