package com.examen.pedidos.service;

import com.examen.pedidos.dto.EstadoRequest;
import com.examen.pedidos.dto.PedidoRequest;
import com.examen.pedidos.dto.PedidoResponse;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoResponse crear(PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setCliente(request.getCliente());
        pedido.setCorreoCliente(request.getCorreoCliente());
        pedido.setProductoId(request.getProductoId());
        pedido.setNombreProducto(request.getNombreProducto());
        pedido.setCantidad(request.getCantidad());
        pedido.setPrecioUnitario(request.getPrecioUnitario());
        // El total se calcula en el backend
        pedido.setTotal(request.getPrecioUnitario().multiply(java.math.BigDecimal.valueOf(request.getCantidad())));
        pedido.setEstado("REGISTRADO");
        pedido.setFechaPedido(LocalDateTime.now());
        return toResponse(pedidoRepository.save(pedido));
    }

    public List<PedidoResponse> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
        return toResponse(pedido);
    }

    public PedidoResponse actualizarEstado(Long id, EstadoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
        pedido.setEstado(request.getEstado().toUpperCase());
        return toResponse(pedidoRepository.save(pedido));
    }

    public void eliminar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
        // Eliminación lógica: cambia estado a CANCELADO
        pedido.setEstado("CANCELADO");
        pedidoRepository.save(pedido);
    }

    private PedidoResponse toResponse(Pedido p) {
        PedidoResponse response = new PedidoResponse();
        response.setId(p.getId());
        response.setCliente(p.getCliente());
        response.setCorreoCliente(p.getCorreoCliente());
        response.setProductoId(p.getProductoId());
        response.setNombreProducto(p.getNombreProducto());
        response.setCantidad(p.getCantidad());
        response.setPrecioUnitario(p.getPrecioUnitario());
        response.setTotal(p.getTotal());
        response.setEstado(p.getEstado());
        response.setFechaPedido(p.getFechaPedido());
        return response;
    }
}
