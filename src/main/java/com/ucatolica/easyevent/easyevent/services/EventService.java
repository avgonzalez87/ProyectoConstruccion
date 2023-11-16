package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return eventoRepository.getAll();
    }

    public Optional<Evento> getEventoById(int id) {
        return eventoRepository.getEvento(id);
    }

    public ResponseEntity<Evento> saveEvento(Evento evento) {

        if (evento.getPrecio()<0){
            evento.setPrecio(0.00);
        }
        if (evento.getCapacidad()<0){
            evento.setCapacidad(0);
        }
        Evento savedEvento = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    public void deleteEvento(Evento evento){
        eventoRepository.delete(evento);
    }
}