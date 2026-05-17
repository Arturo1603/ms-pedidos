package com.examen.pedidos.controller;

import com.examen.pedidos.dto.EstadoRequest;
import com.examen.pedidos.dto.PedidoRequest;
import com.examen.pedidos.dto.PedidoResponse;
import com.examen.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoResponse> actualizarEstado(@PathVariable Long id,
                                                            @Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
