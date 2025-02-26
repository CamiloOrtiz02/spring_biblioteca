package com.egg.biblioteca.Service;

import com.egg.biblioteca.Repository.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio er;
}
