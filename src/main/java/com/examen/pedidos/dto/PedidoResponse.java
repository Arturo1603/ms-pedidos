package com.examen.pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoResponse {
    private Long id;
    private String cliente;
    private String correoCliente;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private String estado;
    private LocalDateTime fechaPedido;
}
