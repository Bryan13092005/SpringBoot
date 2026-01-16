package com.example.pelicula_api.service;

import com.example.pelicula_api.entity.Pelicula;
import com.example.pelicula_api.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class PeliculaService {

        private final PeliculaRepository peliculaRepository;

        public PeliculaService(PeliculaRepository peliculaRepository) {
            this.peliculaRepository = peliculaRepository;
        }

        public Pelicula guardar(Pelicula pelicula) {

            if (pelicula.getAnioEstreno() != null && pelicula.getAnioEstreno() > 2026) {
                throw new IllegalArgumentException("El año de estreno no puede ser futuro");
            }

            if (!pelicula.isDisponible()) {
                throw new IllegalStateException("La película debe estar disponible");
            }

            return peliculaRepository.save(pelicula);
        }


        public List<Pelicula> listar() {
            return peliculaRepository.findAll();
        }
    }


