package com.perfulandia.reportes.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Column(name = "tipo_reporte", nullable = false)
    private String tipoReporte;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "json_datos", columnDefinition = "TEXT")
    private String jsonDatos;
}