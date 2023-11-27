package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public void actualizarEvento(Integer id, Evento eventoActualizado) throws Exception {
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            Evento eventoExistente = evento.get();
            eventoExistente.setNombreEvento(eventoActualizado.getNombreEvento());
            eventoExistente.setDescripcion(eventoActualizado.getDescripcion());
            eventoExistente.setTipoEvento(eventoActualizado.getTipoEvento());
            eventoExistente.setActividades(eventoActualizado.getActividades());
            eventoExistente.setComida(eventoActualizado.getComida());
            eventoExistente.setEstado(eventoActualizado.getEstado());
            eventoExistente.setDescripcion(eventoActualizado.getDescripcion());
            eventoExistente.setUbicacion(eventoActualizado.getUbicacion());
            eventoExistente.setCategoria(eventoActualizado.getCategoria());
            Date fechaActualizada = eventoActualizado.getFecha();
            Instant instant = fechaActualizada.toInstant();
            Date fechaUTC = Date.from(instant.atZone(ZoneId.of("UTC")).toInstant());
            eventoExistente.setFechaEvento(fechaUTC);
            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDateTime fechaMaximaPermitida = fechaActual.plusMonths(3);
            LocalDateTime fechaEventoActualizada = LocalDateTime.ofInstant(fechaUTC.toInstant(), ZoneId.of("UTC"));

            if (fechaEventoActualizada.isAfter(fechaMaximaPermitida)) {
                throw new RuntimeException("La fecha actualizada excede el límite de 3 meses.");
            }
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

            eventoRepository.save(eventoExistente);
        } else {
            throw new Exception("Evento no encontrado con ID: " + id);
        }
    }
}