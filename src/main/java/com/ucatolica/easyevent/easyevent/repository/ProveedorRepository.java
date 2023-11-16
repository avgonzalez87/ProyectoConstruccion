package com.ucatolica.easyevent.easyevent.repository;

import com.ucatolica.easyevent.easyevent.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {

}