package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;

import com.ucatolica.easyevent.easyevent.model.Proveedor;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ProveedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ucatolica.easyevent.easyevent.repository.ProveedorRepository;
//import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
@Tag(name="EventController", description = "Esto gestiona las operaciones que se realiza el evento")
@RestController
@RequestMapping("/api/evento")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/eventos")
    public List<Evento> getAll(){

        return eventService.getAllEvents();
    }

    @GetMapping("/eventos/{id}")
    public Optional<Evento> getEvento(@PathVariable int id){
        return eventService.getEventoById(id);
    }

    @PostMapping("/eventos/save")
    public ResponseEntity<String> crearEvento( @RequestBody Evento evento) {
        try {
            eventService.saveEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> actualizarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        try {
            eventService.actualizarEvento(id, evento);
            return ResponseEntity.ok("Evento actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el evento: " + e.getMessage());
        }
    }

    @DeleteMapping("/eventos/delete")
    public void deleteEvento(@RequestBody Evento evento){
        eventService.deleteEvento(evento);
    }
}