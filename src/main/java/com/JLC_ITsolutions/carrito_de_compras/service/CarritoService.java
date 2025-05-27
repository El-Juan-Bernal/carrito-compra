package com.JLC_ITsolutions.carrito_de_compras.service;

import java.util.List;

import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoRequest;
import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoResponse;


public interface CarritoService {
    CarritoResponse crearCarrito(CarritoRequest carritoRequest);
    CarritoResponse obtenerCarritoPorId(int idCompra);
    CarritoResponse agregarProducto(int idCompra, int cantidad, double valor);
    CarritoResponse eliminarProducto(int idCompra, int cantidad, double valor);
    void vaciarCarrito(int idCompra);
    void eliminarCarrito(int idCompra);
    List<CarritoResponse> obtenerTodosLosCarritos();
    
}
