package com.perfulandia.reportes.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes")
public class Reportes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Column(name = "tipo_reporte", nullable = false)
    private String tipoReporte;

    @NotNull(message = "La fecha de generación no puede ser nula")
    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Lob
    @Column(name = "json_datos")
    private String jsonDatos;

    //getters manuales
    public Long getIdReporte() {
        return idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getJsonDatos() {
        return jsonDatos;
    }

    //setters manuales si es q los necesito (para JPA o POST requests)
    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setJsonDatos(String jsonDatos) {
        this.jsonDatos = jsonDatos;
    }
}
