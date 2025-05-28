package com.JLC_ITsolutions.carrito_de_compras.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponse {
private int idCompra;
    private int cantidadProductos;
    private double valorProducto;
    private double valorTotal;
    123
}
