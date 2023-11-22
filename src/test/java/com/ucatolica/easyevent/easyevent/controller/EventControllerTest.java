package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventController.class)
@ActiveProfiles("test")
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @Test
    public void RegistroEvento() throws Exception {
        Evento evento = new Evento();

    }

}
