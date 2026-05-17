package com.examen.pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstadoRequest {
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}
