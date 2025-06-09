package com.perfulandia.reportes.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfulandia.reportes.dto.ReportesDTO;
import com.perfulandia.reportes.service.ReportesService;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    // 📊 Obtener reportes de ventas por período con validación
    @GetMapping("/ventas/periodo")
    public ResponseEntity<List<ReportesDTO>> obtenerReportesVentasPorPeriodo(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        if (inicio.isAfter(fin)) {
            return ResponseEntity.badRequest().body(null);
        }
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

    // 🔍 Obtener un reporte específico por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<ReportesDTO> obtenerReportePorId(@PathVariable Long id) {
        ReportesDTO reporte = reportesService.obtenerReportePorId(id);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}