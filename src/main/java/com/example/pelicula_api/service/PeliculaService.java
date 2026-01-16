package com.example.pelicula_api.service;

import com.example.pelicula_api.entity.Pelicula;
import com.example.pelicula_api.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class PeliculaService {

        private final PeliculaRepository peliculaRepository;

        public PeliculaService(PeliculaRepository peliculaRepository) throw new RecursoNoEncontradoException("Película no encontrada");
{
            this.peliculaRepository = peliculaRepository;
        }
        public Pelicula buscarPorId(Long id) throw new RecursoNoEncontradoException("Película no encontrada");
{
            return peliculaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Película no encontrada"));
        }

        public void eliminar(Long id) throw new RecursoNoEncontradoException("Película no encontrada");
{
            if (!peliculaRepository.existsById(id)) {
                throw new RuntimeException("No existe la película");
            }
            peliculaRepository.deleteById(id);
        }
        public Pelicula guardar(Pelicula pelicula) throw new RecursoNoEncontradoException("Película no encontrada");
{

            if (pelicula.getAnioEstreno() != null && pelicula.getAnioEstreno() > 2026) {
                throw new IllegalArgumentException("El año de estreno no puede ser futuro");
            }

            if (!pelicula.isDisponible()) {
                throw new IllegalStateException("La película debe estar disponible");
            }

            return peliculaRepository.save(pelicula);
        }



        public List<Pelicula> listar() throw new RecursoNoEncontradoException("Película no encontrada");
{
            return peliculaRepository.findAll();
        }

        public Pelicula actualizar(Long id, Pelicula pelicula) throw new RecursoNoEncontradoException("Película no encontrada");
{
        Pelicula existente = buscarPorId(id);

            existente.setTitulo(pelicula.getTitulo());
            existente.setGenero(pelicula.getGenero());
            existente.setDuracion(pelicula.getDuracion());
            existente.setAnioEstreno(pelicula.getAnioEstreno());
            existente.setDisponible(pelicula.isDisponible());

            return peliculaRepository.save(existente);
    }

    }


