package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.model.Proveedor;
import com.ucatolica.easyevent.easyevent.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProveedorService {

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    private ProveedorRepository proveedorRepository;

    public Optional<Proveedor> getProveedorById(Integer id) {return proveedorRepository.findById(id);}
}
