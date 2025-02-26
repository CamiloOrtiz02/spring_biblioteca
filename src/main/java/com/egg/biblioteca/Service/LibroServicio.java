package com.egg.biblioteca.Service;

import com.egg.biblioteca.Entity.Libro;
import com.egg.biblioteca.Repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio lr;

//    public Optional<Libro> buscarPorTitulo(String titulo) {
//        return Optional.ofNullable(lr.buscarPorTitulo(titulo));
//    }
    public Libro buscarPorTitulo(String titulo) {
        return lr.buscarPorTitulo(titulo);
    }
}