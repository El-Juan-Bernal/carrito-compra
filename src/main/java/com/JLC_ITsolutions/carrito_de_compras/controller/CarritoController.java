package com.JLC_ITsolutions.carrito_de_compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoRequest;
import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoResponse;
import com.JLC_ITsolutions.carrito_de_compras.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoResponse> crearCarrito(@RequestBody CarritoRequest request) {
        return ResponseEntity.ok(carritoService.crearCarrito(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoResponse> obtenerCarritoPorId(@PathVariable int id) {
        return ResponseEntity.ok(carritoService.obtenerCarritoPorId(id));
    }

    @PutMapping("/{id}/agregar")
    public ResponseEntity<CarritoResponse> agregarProducto(
            @PathVariable int id,
            @RequestParam int cantidad,
            @RequestParam double valor) {
        return ResponseEntity.ok(carritoService.agregarProducto(id, cantidad, valor));
    }

    @PutMapping("/{id}/eliminar")
    public ResponseEntity<CarritoResponse> eliminarProducto(
            @PathVariable int id,
            @RequestParam int cantidad,
            @RequestParam double valor) {
        return ResponseEntity.ok(carritoService.eliminarProducto(id, cantidad, valor));
    }

    @PutMapping("/{id}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable int id) {
        carritoService.vaciarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable int id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarritoResponse>> obtenerTodosLosCarritos() {
        return ResponseEntity.ok(carritoService.obtenerTodosLosCarritos());
    }
}
