package com.ucatolica.easyevent.easyevent.controller;
import com.ucatolica.easyevent.easyevent.model.Usuario;
import com.ucatolica.easyevent.easyevent.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Tag(name = "UsuarioController", description = "Gestiona las operaciones relacionadas con usuarios")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve un usuario basado en su ID")
    @GetMapping("/getusuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
