package com.perfulandia.reportes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.reportes.dto.ReportesDTO;
import com.perfulandia.reportes.service.ReportesService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReportesService reportesService;

    public ReporteController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    // 📊 Obtener reportes de ventas por período
    @GetMapping("/ventas/periodo")
    public ResponseEntity<List<ReportesDTO>> obtenerReportesVentasPorPeriodo(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        return ResponseEntity.ok(reportesService.obtenerReportesVentasPorPeriodo(inicio, fin));
    }

    // 🏆 Obtener los vendedores con mejores ventas
    @GetMapping("/vendedores/top")
    public ResponseEntity<List<ReportesDTO>> obtenerTopVendedores() {
        return ResponseEntity.ok(reportesService.obtenerTopVendedores());
    }

    // 📦 Obtener productos con inventario crítico
    @GetMapping("/inventario/critico")
    public ResponseEntity<List<ReportesDTO>> obtenerInventarioCritico() {
        return ResponseEntity.ok(reportesService.obtenerInventarioCritico());
    }

    // 🔍 Obtener un reporte específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReportesDTO> obtenerReportePorId(@PathVariable Long id) {
        return ResponseEntity.ok(reportesService.obtenerReportePorId(id));
    }
}