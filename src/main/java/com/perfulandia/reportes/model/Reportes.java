package com.perfulandia.reportes.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "reportes")
public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @Column(name = "tipo_reporte", nullable = false)
    private String tipoReporte;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(name = "descripcion", length = 255)
    private String descripcion; 

    @Column(name = "json_datos", columnDefinition = "TEXT")
    private String jsonDatos; 

    // Getters y Setters
    public Long getIdReporte() { return idReporte; }
    public void setIdReporte(Long idReporte) { this.idReporte = idReporte; }

    public String getTipoReporte() { return tipoReporte; }
    public void setTipoReporte(String tipoReporte) { this.tipoReporte = tipoReporte; }

    public LocalDate getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDate fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getJsonDatos() { return jsonDatos; }
    public void setJsonDatos(String jsonDatos) { this.jsonDatos = jsonDatos; }

    
}
