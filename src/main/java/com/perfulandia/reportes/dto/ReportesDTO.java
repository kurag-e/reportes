package com.perfulandia.reportes.dto;

import java.time.LocalDate;

public class ReportesDTO {
    private Long idReporte;
    private String tipoReporte;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private String jsonDatos;

    // Constructor
    public ReportesDTO(Long idReporte, String tipoReporte, LocalDate fechaGeneracion, String descripcion, String jsonDatos) {
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.descripcion = descripcion;
        this.jsonDatos = jsonDatos;
    }

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
