package com.ucatolica.easyevent.easyevent.repository;
import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.repository.EventoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class EventoRepository implements Serializable {

    @Autowired
    private EventoCrudRepository eventoCrudRepository;

    public List<Evento> getAll(){return (List<Evento>) eventoCrudRepository.findAll();}
    public Optional<Evento> getEvento(int id){return eventoCrudRepository.findById(id);}

    public Evento save(Evento evento){return eventoCrudRepository.save(evento);}

    public void delete(Evento evento){eventoCrudRepository.delete(evento);}
}
