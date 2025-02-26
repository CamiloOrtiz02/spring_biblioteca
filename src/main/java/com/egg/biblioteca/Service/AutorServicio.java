package com.egg.biblioteca.Service;

import com.egg.biblioteca.Repository.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio ar;
}
