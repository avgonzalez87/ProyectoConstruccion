package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.model.LoginDTO;
import com.ucatolica.easyevent.easyevent.model.Usuario;
import com.ucatolica.easyevent.easyevent.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return Optional.of(usuarioRepository.save(usuario));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean loggin(LoginDTO credenciales) {
        String correo = credenciales.getCorreo();
        String contrasena = credenciales.getContrasena();
        try {
            // Buscar el usuario por correo
            Usuario usuario = usuarioRepository.findByCorreo(correo);

            // Verificar si se encontró un usuario con el correo dado
            if (usuario != null) {
                // Comparar contraseñas en texto plano
                if (usuario.getContrasena().equals(contrasena)) {
                    // Credenciales válidas
                    return true;
                } else {
                    // Contraseña incorrecta
                    return false;
                }
            } else {
                // No se encontró un usuario con el correo dado
                return false;
            }


        } catch (RuntimeException e) {
            throw new RuntimeException("Error interno en el sistema.");
        }


    }

}


