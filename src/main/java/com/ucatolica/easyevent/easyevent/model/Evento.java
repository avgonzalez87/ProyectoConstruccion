package com.ucatolica.easyevent.easyevent.model;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id", nullable = false)
    private Integer id;

    @Column(name = "idproveedor", length = Integer.MAX_VALUE)
    private Integer idproveedor;

    @Column(name = "nombre_evento", length = 100)
    private String nombreEvento;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "tipo_evento", length = 50)
    private String tipoEvento;

    @Column(name = "edad_recomendada")
    private Integer edadRecomendada;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "actividades", length = Integer.MAX_VALUE)
    private String actividades;

    @Column(name = "ubicacion", length = Integer.MAX_VALUE)
    private String ubicacion;

    @Column(name = "georeferencia", length = Integer.MAX_VALUE)
    private String georeferencia;

    @Column(name = "categoria", length = Integer.MAX_VALUE)
    private String categoria;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "comida", length = Integer.MAX_VALUE)
    private String comida;

    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;


    @Column(name = "fechaEvento",length = Integer.MAX_VALUE))
    private  String  fechaEvento;

    public Evento(Integer id, Integer idproveedor, String nombreEvento, String descripcion, String tipoEvento, Integer edadRecomendada, String fechaEvento, Double precio, String actividades, String ubicacion, String georeferencia, String categoria, Integer capacidad, String comida, String estado) {
        this.id = id;
        this.idproveedor = idproveedor;
        this.nombreEvento = nombreEvento;
        this.descripcion = descripcion;
        this.tipoEvento = tipoEvento;
        this.edadRecomendada = edadRecomendada;
        this.precio = precio;
        this.actividades = actividades;
        this.ubicacion = ubicacion;
        this.georeferencia = georeferencia;
        this.categoria = categoria;
        this.capacidad = capacidad;
        this.comida = comida;
        this.estado = estado;
        this.fechaEvento= fechaEvento;

    }

    public Evento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getEdadRecomendada() {
        return edadRecomendada;
    }

    public void setEdadRecomendada(Integer edadRecomendada) {
        this.edadRecomendada = edadRecomendada;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getGeoreferencia() {
        return georeferencia;
    }

    public void setGeoreferencia(String georeferencia) {
        this.georeferencia = georeferencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public  String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Integer fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
}