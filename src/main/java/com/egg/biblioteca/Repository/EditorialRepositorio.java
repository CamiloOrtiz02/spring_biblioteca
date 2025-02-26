package com.egg.biblioteca.Repository;

import com.egg.biblioteca.Entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Long> {
}
