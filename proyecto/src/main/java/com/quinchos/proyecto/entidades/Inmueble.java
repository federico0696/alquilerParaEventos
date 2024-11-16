package com.quinchos.proyecto.entidades;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;



@Entity
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Permite que Hibernate maneje la generación de UUID
    @Column(name = "inmueble_id", nullable = false, updatable = false)
    private Long inmuebleId;

    // Relación con el propietario (Usuario con rol PROPIETARIO)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id", nullable = false)
    private Usuario propietario;

    // Relación con el inquilino (Usuario con rol INQUILINO)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquilino_id", nullable = true) // Permite que sea nulo si no hay inquilino
    private Usuario inquilino;

    // Uso de Enum para la categoría del inmueble
    @Enumerated(EnumType.STRING) // Almacena el valor como una cadena de texto en la base de datos
    @Column(nullable = false)
    private CategoriaInmueble categoria;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "La localidad es obligatoria.")
    private String localidad;

    @Column(length = 255)
    private String ubicacion;

    @Column(nullable = false)
    @Positive(message = "La capacidad debe ser mayor que cero.")
    private Integer capacidadPersonas;

    private boolean disponible;

    @Column(length = 500)
    private String descripcion;

    private Integer superficie;

    private String imagen;

    @ElementCollection
    @CollectionTable(name = "inmueble_servicios", joinColumns = @JoinColumn(name = "inmueble_id"))
    @Column(name = "servicio")
    private List<String> servicios;

    @ElementCollection
    @CollectionTable(name = "inmueble_comentarios", joinColumns = @JoinColumn(name = "inmueble_id"))
    @Column(name = "comentario")
    private List<String> comentarios;

    private Integer precioDia;

    // Constructor por defecto
    public Inmueble() {
    }

    // Getters y Setters
    public Long getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(Long inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Usuario getInquilino() {
        return inquilino;
    }

    public void setInquilino(Usuario inquilino) {
        this.inquilino = inquilino;
    }

    public CategoriaInmueble getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaInmueble categoria) {
        this.categoria = categoria;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Integer precioDia) {
        this.precioDia = precioDia;
    }

    // Queda listo para comparar objetos si es necesario
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Inmueble inmueble = (Inmueble) o;
        return inmuebleId != null && inmuebleId.equals(inmueble.inmuebleId);
    }

    @Override
    public int hashCode() {
        return 31 * (inmuebleId != null ? inmuebleId.hashCode() : 0);
    }
}
