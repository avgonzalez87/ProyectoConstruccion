package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Service

public class EventService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(int id) {
        return eventoRepository.findById(id);
    }

    public void saveEvento(Evento evento) {
        try {
            if (evento.getNombreEvento() == null || evento.getNombreEvento().isEmpty() ||
                    evento.getDescripcion() == null || evento.getDescripcion().isEmpty() ||
                    evento.getUbicacion() == null || evento.getUbicacion().isEmpty()) {
                throw new RuntimeException("Campos incompletos");
            }

            LocalDateTime fechaActual = LocalDateTime.parse(evento.getFechaEvento());
            LocalDateTime fechaMaximaPermitida = fechaActual.plusDays(1);
            Instant instant = fechaMaximaPermitida.atZone(ZoneId.systemDefault()).toInstant();
            evento.setFechaEvento(Date.from(instant));

            if (evento.getPrecio() < 0) {
                evento.setPrecio(0.00);
            }
            if (evento.getCapacidad() < 0) {
                evento.setCapacidad(0);
            }

            eventoRepository.save(evento);

        } catch (Exception e){
            throw new RuntimeException("Error: "+e.getMessage());


        }


    }

    public void deleteEvento(Evento evento){
        eventoRepository.delete(evento);
    }
    public ResponseEntity<String> updateEvento(int eventoId, Evento eventoActualizado) {
        Optional<Evento> optionalEvento = eventoRepository.findById(eventoId);

        if (optionalEvento.isPresent()) {
            Evento eventoExistente = optionalEvento.get();

            // Actualizar los campos relevantes del evento existente con la información proporcionada
            eventoExistente.setNombreEvento(eventoActualizado.getNombreEvento());
            eventoExistente.setDescripcion(eventoActualizado.getDescripcion());
            eventoExistente.setTipoEvento(eventoActualizado.getTipoEvento());
            eventoExistente.setActividades(eventoActualizado.getActividades());
            eventoExistente.setComida(eventoActualizado.getComida());
            eventoExistente.setEstado(eventoActualizado.getEstado());
            eventoExistente.setDescripcion(eventoActualizado.getDescripcion());
            eventoExistente.setUbicacion(eventoActualizado.getUbicacion());
            eventoExistente.setCategoria(eventoActualizado.getCategoria());

            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDateTime fechaMaximaPermitida = fechaActual.plusMonths(3);

            try {
                LocalDateTime fechaEventoActualizada = LocalDateTime.parse(eventoActualizado.getFechaEvento()); // Intentar convertir la cadena de fecha

                if (fechaEventoActualizada.isAfter(fechaMaximaPermitida)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fecha actualizada excede el límite de 3 meses.");
                }

                // Resto del código para actualizar y guardar el evento...
            } catch (DateTimeParseException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de fecha incorrecto para la fecha actualizada.");
            }
            // Aplicar las validaciones necesarias
            if (eventoActualizado.getPrecio() < 0) {
                eventoExistente.setPrecio(0.00);
            } else {
                eventoExistente.setPrecio(eventoActualizado.getPrecio());
            }

            if (eventoActualizado.getCapacidad() < 0) {
                eventoExistente.setCapacidad(0);
            } else {
                eventoExistente.setCapacidad(eventoActualizado.getCapacidad());
            }

            // Guardar el evento actualizado
             eventoRepository.save(eventoExistente);
            return ResponseEntity.status(HttpStatus.OK).body("Evento actualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }
    }
}