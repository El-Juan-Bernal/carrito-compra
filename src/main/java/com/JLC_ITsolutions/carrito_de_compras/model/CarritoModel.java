package com.JLC_ITsolutions.carrito_de_compras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_compras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompra;
    
    @Column(name = "cantidad_productos")
    private int cantidadProductos;
    
    @Column(name = "valor_producto")
    private double valorProducto;
    
    @Column(name = "valor_total")
    private double valorTotal;
    
    // agregar producto
    public void agregarProducto(int cantidad, double valor) {
        this.cantidadProductos += cantidad;
        this.valorProducto += valor;
        calcularValorTotal();
    }
    
    // eliminar producto
    public void eliminarProducto(int cantidad, double valor) {
        if (this.cantidadProductos >= cantidad && this.valorProducto >= valor) {
            this.cantidadProductos -= cantidad;
            this.valorProducto -= valor;
            calcularValorTotal();
        }
    }
    
    // vaciar carrito
    public void vaciarCarrito() {
        this.cantidadProductos = 0;
        this.valorProducto = 0.0;
        this.valorTotal = 0.0;
    }
    
    // calcular valor total
    public void calcularValorTotal() {
        this.valorTotal = this.valorProducto;
    }
    
}
