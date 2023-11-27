package com.ucatolica.easyevent.easyevent.repository;
import com.ucatolica.easyevent.easyevent.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {


}