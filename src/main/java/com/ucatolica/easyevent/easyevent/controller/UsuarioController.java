package com.ucatolica.easyevent.easyevent.controller;
import com.ucatolica.easyevent.easyevent.model.LoginDTO;
import com.ucatolica.easyevent.easyevent.model.Usuario;
import com.ucatolica.easyevent.easyevent.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PostMapping("/listall")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);
        return usuarioOptional.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioService.updateUsuario(id, usuario);
        return usuarioOptional.map(usuarioActualizado -> new ResponseEntity<>(usuarioActualizado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/auth")
    public ResponseEntity<String> autenticacionUsuario(@Valid @RequestBody LoginDTO credenciales) {
        try{//intenta todo lo que esta adentro
            Boolean loggin = usuarioService.loggin(credenciales);
            if (loggin){
                return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
            }
            else {

                return new ResponseEntity<>("Credenciales invalidas, intente nuevamente", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (RuntimeException e){
            return new ResponseEntity<>("Error interno del servidor, intente nuevamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
