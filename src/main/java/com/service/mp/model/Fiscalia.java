package com.service.mp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "fiscalia")
public class Fiscalia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "name de fiscalia obligatorio")
    @Length(min = 0, max = 100, message = "name no puede ser mayor a 100 caracteres")
    private String name;

    @NotBlank(message = "telefono es obligatorio")
    @Length(min = 8, max = 15, message = "telefono tiene que tener 8 a 15 caracteres")
    @Pattern(regexp = "\\d{4}\\-\\d{4}$", message = "formato debe ser 0000-0000")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    Fiscalia() {
    }

    Fiscalia(String name, String telefono, Ubicacion ubicacion) {
        this.name = name;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Fiscalia [id=" + id + ", name=" + name + ", telefono=" + telefono + ", ubicacion=" + ubicacion + "]";
    }
}
