package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public String validarContrasena(String nuevaContrasenia) {
        if (nuevaContrasenia.length() < 8) {
            return "La contraseña debe tener al menos 8 caracteres.";
        }
        if (!nuevaContrasenia.matches(".*[A-Z].*")) {
            return "La contraseña debe tener al menos una letra mayúscula.";
        }
        if (!nuevaContrasenia.matches(".*[a-z].*")) {
            return "La contraseña debe tener al menos una letra minúscula.";
        }
        if (!nuevaContrasenia.matches(".*[0-9].*")) {
            return "La contraseña debe tener al menos un número.";
        }
        if (!nuevaContrasenia.matches(".*[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return "La contraseña debe tener al menos un símbolo.";
        }
        return "ok";
    }
}
