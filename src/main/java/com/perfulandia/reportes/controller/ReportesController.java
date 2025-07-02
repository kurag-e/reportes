package com.perfulandia.reportes.controller; //v2 xq hateoas

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.reportes.dto.ReportesDTO;
import com.perfulandia.reportes.service.ReportesService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    //obtener reportes x periodo
    @GetMapping("/ventas/periodo")
    public ResponseEntity<CollectionModel<EntityModel<ReportesDTO>>> obtenerReportesVentasPorPeriodo(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        if (inicio.isAfter(fin)) {
            return ResponseEntity.badRequest().body(null);
        }

        List<ReportesDTO> reportes = reportesService.obtenerReportesVentasPorPeriodo(inicio, fin);

        List<EntityModel<ReportesDTO>> modelos = reportes.stream()
            .map(r -> EntityModel.of(r,
                linkTo(methodOn(ReportesController.class).obtenerReportePorId(r.getIdReporte())).withSelfRel()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(modelos,
                linkTo(methodOn(ReportesController.class).obtenerReportesVentasPorPeriodo(inicio, fin)).withSelfRel())
        );
    }

    //obtener top vendedores
    @GetMapping("/vendedores/top")
    public ResponseEntity<CollectionModel<EntityModel<ReportesDTO>>> obtenerTopVendedores() {
        List<EntityModel<ReportesDTO>> modelos = reportesService.obtenerTopVendedores().stream()
            .map(r -> EntityModel.of(r,
                linkTo(methodOn(ReportesController.class).obtenerReportePorId(r.getIdReporte())).withSelfRel()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(modelos,
                linkTo(methodOn(ReportesController.class).obtenerTopVendedores()).withSelfRel())
        );
    }

    //obtener inventario critico
    @GetMapping("/inventario/critico")
    public ResponseEntity<CollectionModel<EntityModel<ReportesDTO>>> obtenerInventarioCritico() {
        List<EntityModel<ReportesDTO>> modelos = reportesService.obtenerInventarioCritico().stream()
            .map(r -> EntityModel.of(r,
                linkTo(methodOn(ReportesController.class).obtenerReportePorId(r.getIdReporte())).withSelfRel()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(modelos,
                linkTo(methodOn(ReportesController.class).obtenerInventarioCritico()).withSelfRel())
        );
    }

    //reporte por id con hateoas
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReportesDTO>> obtenerReportePorId(@PathVariable Long id) {
        ReportesDTO reporte = reportesService.obtenerReportePorId(id);
        if (reporte != null) {
            EntityModel<ReportesDTO> modelo = EntityModel.of(reporte,
                linkTo(methodOn(ReportesController.class).obtenerReportePorId(id)).withSelfRel(),
                linkTo(methodOn(ReportesController.class).obtenerTopVendedores()).withRel("top-vendedores"),
                linkTo(methodOn(ReportesController.class).obtenerInventarioCritico()).withRel("inventario-critico")
            );
            return ResponseEntity.ok(modelo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
