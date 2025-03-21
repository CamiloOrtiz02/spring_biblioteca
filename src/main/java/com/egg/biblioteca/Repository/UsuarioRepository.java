package com.egg.biblioteca.Repository;

import com.egg.biblioteca.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
}
