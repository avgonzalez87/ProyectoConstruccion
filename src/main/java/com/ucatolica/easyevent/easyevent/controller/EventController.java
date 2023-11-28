package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EventController", description = "Gestiona las operaciones relacionadas con eventos")
@RestController
@RequestMapping("/api/evento")
public class EventController {
    @Autowired
    private EventService eventService;

    @Operation(summary = "Obtener todos los eventos", description = "Devuelve una lista de todos los eventos")
    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.getAllEvents());
    }

    @Operation(summary = "Obtener un evento por ID", description = "Devuelve un evento basado en su ID")
    @ApiResponse(responseCode = "400", description = "Error interno")
    @ApiResponse(responseCode = "403", description = "Acceso prohibido")
    @GetMapping("/eventos/list/{id}")
    public ResponseEntity<Evento> getEvento(
            @Parameter(description = "ID del evento a recuperar", required = true)
            @PathVariable int id
    ){
        Evento evento = eventService.getEventoById(id);
        if(evento != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(evento);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo evento", description = "Crea y guarda un nuevo evento")
    @ApiResponse(responseCode = "201", description = "Evento creado exitosamente")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @ApiResponse(responseCode = "403", description = "Acceso prohibido")
    @PostMapping("/eventos/save")
    public ResponseEntity<String> crearEvento(@Valid
                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                      description = "Evento a ser creado",
                                                      required = true,
                                                      content = @Content(schema = @Schema(implementation = Evento.class))
                                              )
                                              @RequestBody Evento evento) {
        try {
            eventService.saveEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un evento por ID", description = "Actualiza un evento existente")
    @ApiResponse(responseCode = "200", description = "Evento actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error interno")
    @ApiResponse(responseCode = "403", description = "Acceso prohibido")
    @PutMapping("/update/{eventoId}")
    public ResponseEntity<String> actualizarEvento(
            @Parameter(description = "ID del evento a ser actualizado", required = true)
            @PathVariable int eventoId,
            @Parameter(description = "Evento actualizado", required = true)
            @RequestBody Evento eventoActualizado) {

        return eventService.updateEvento(eventoId, eventoActualizado);
    }

    @Operation(summary = "Eliminar un evento", description = "Elimina un evento existente")
    @ApiResponse(responseCode = "200", description = "Evento eliminado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error interno")
    @ApiResponse(responseCode = "403", description = "Acceso prohibido")
    @DeleteMapping("/eventos/delete/{eventoId}")
    public void deleteEvento(
            @Parameter(description = "ID del evento a ser eliminado", required = true)
            @PathVariable Integer eventoId
    ) {
        eventService.deleteEvento(eventoId);
    }
}
