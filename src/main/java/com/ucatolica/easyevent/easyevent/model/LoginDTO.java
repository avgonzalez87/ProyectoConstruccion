package com.ucatolica.easyevent.easyevent.model;


import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "El correo no puede ser nulo.")
    private String correo;

    @NotNull(message = "La contrasena no puede ser nulo.")
    private String contrasena;

    public LoginDTO() {
    }

    public LoginDTO(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
