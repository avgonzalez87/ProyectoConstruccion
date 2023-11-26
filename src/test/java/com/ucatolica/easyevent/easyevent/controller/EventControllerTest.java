package com.ucatolica.easyevent.easyevent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@WebMvcTest(EventController.class)
@ActiveProfiles("test")
@Import(EventControllerTest.EventControllerTestConfig.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventService eventService;

    @Test
    public void RegistroEvento() throws Exception {
        Evento evento = new Evento();
        evento.setId(43534);
        evento.setTipoEvento("a");
        evento.setPrecio(2000.00);
        evento.setCapacidad(20);
        evento.setComida("normal");
        evento.setNombreEvento("evento1");
        evento.setDescripcion("descripcion");
        evento.setActividades("actividad1");
        evento.setCategoria("niños");
        evento.setEstado("activo");
        evento.setUbicacion("bogota");
        evento.setEdadRecomendada(15);
        evento.setGeoreferencia("bogota");
        evento.setIdproveedor(1);
        Mockito.doNothing().when(eventService).saveEvento(any(Evento.class));

        mockMvc.perform(post("/api/evento/eventos/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(evento)))
                .andExpect(status().isCreated());
    }

    @TestConfiguration
    public static class EventControllerTestConfig {

        @MockBean
        private EventService eventService;


    }
}