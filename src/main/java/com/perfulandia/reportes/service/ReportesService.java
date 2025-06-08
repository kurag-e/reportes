package com.perfulandia.reportes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perfulandia.reportes.dto.ReportesDTO;
import com.perfulandia.reportes.model.Reportes;
import com.perfulandia.reportes.repository.ReportesRepository;

@Service
public class ReportesService {
    private final ReportesRepository reportesRepository;

    public ReportesService(ReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }

    // 🔍 Obtener reportes de ventas por período
    public List<ReportesDTO> obtenerReportesVentasPorPeriodo(LocalDate inicio, LocalDate fin) {
        return reportesRepository.findByFechaGeneracionBetween(inicio, fin)
                .stream()
                .map(reporte -> new ReportesDTO(
                        reporte.getIdReporte(),
                        reporte.getTipoReporte(),
                        reporte.getFechaGeneracion(),
                        reporte.getDescripcion(),
                        reporte.getJsonDatos()))
                .collect(Collectors.toList());
    }

    // 🏆 Obtener los vendedores con mejores ventas
    public List<ReportesDTO> obtenerTopVendedores() {
        return reportesRepository.findTopVendedores()
                .stream()
                .map(reporte -> new ReportesDTO(
                        reporte.getIdReporte(),
                        reporte.getTipoReporte(),
                        reporte.getFechaGeneracion(),
                        reporte.getDescripcion(),
                        reporte.getJsonDatos()))
                .collect(Collectors.toList());
    }

    // 📦 Obtener productos con inventario crítico
    public List<ReportesDTO> obtenerInventarioCritico() {
        return reportesRepository.findInventarioCritico()
                .stream()
                .map(reporte -> new ReportesDTO(
                        reporte.getIdReporte(),
                        reporte.getTipoReporte(),
                        reporte.getFechaGeneracion(),
                        reporte.getDescripcion(),
                        reporte.getJsonDatos()))
                .collect(Collectors.toList());
    }

    // 🔍 Obtener un reporte por ID
    public ReportesDTO obtenerReportePorId(Long id) {
        Reportes reporte = reportesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        return new ReportesDTO(
                reporte.getIdReporte(),
                reporte.getTipoReporte(),
                reporte.getFechaGeneracion(),
                reporte.getDescripcion(),
                reporte.getJsonDatos());
    }
}