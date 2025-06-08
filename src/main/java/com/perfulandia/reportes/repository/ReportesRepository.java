package com.perfulandia.reportes.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perfulandia.reportes.model.Reportes;

@Repository
public interface ReportesRepository extends JpaRepository<Reportes, Long> {
    
    // 🔍 Buscar reportes por rango de fechas
    List<Reportes> findByFechaGeneracionBetween(LocalDate inicio, LocalDate fin);

    // 🏆 Obtener los vendedores con mejores ventas
    @Query("SELECT r FROM Reportes r WHERE r.tipoReporte = 'ventas' ORDER BY r.datosReporte DESC")
    List<Reportes> findTopVendedores();

    // 📦 Obtener reportes con inventario crítico
    @Query("SELECT r FROM Reportes r WHERE r.tipoReporte = 'inventario' AND r.datosReporte LIKE '%critico%'")
    List<Reportes> findInventarioCritico();
}
