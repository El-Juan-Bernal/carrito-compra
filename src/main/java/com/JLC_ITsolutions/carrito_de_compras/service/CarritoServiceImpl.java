package com.JLC_ITsolutions.carrito_de_compras.service;

import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoRequest;
import com.JLC_ITsolutions.carrito_de_compras.DTO.CarritoResponse;
import com.JLC_ITsolutions.carrito_de_compras.Exception.CarritoNotFoundException;
import com.JLC_ITsolutions.carrito_de_compras.model.CarritoModel;
import com.JLC_ITsolutions.carrito_de_compras.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarritoServiceImpl implements CarritoService{
    private final CarritoRepository carritoRepository;

    @Override
    public CarritoResponse crearCarrito(CarritoRequest carritoRequest) {
        log.info("Creando nuevo carrito de compras");

        CarritoModel carrito = CarritoModel.builder()
                .cantidadProductos(carritoRequest.getCantidadProductos())
                .valorProducto(carritoRequest.getValorProducto())
                .build();

        carrito.calcularValorTotal();
        CarritoModel carritoGuardado = carritoRepository.save(carrito);

        log.info("Carrito creado con ID: {}", carritoGuardado.getIdCompra());
        return mapToCarritoResponse(carritoGuardado);
    }

    @Override
    public CarritoResponse obtenerCarritoPorId(int idCompra) {
        CarritoModel carrito = carritoRepository.findById(idCompra)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado con ID: " + idCompra));

        return mapToCarritoResponse(carrito);
    }

    @Override
    public CarritoResponse agregarProducto(int idCompra, int cantidad, double valor) {
        CarritoModel carrito = carritoRepository.findById(idCompra)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado con ID: " + idCompra));

        carrito.agregarProducto(cantidad, valor);
        return mapToCarritoResponse(carritoRepository.save(carrito));
    }

    @Override
    public CarritoResponse eliminarProducto(int idCompra, int cantidad, double valor) {
        CarritoModel carrito = carritoRepository.findById(idCompra)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado con ID: " + idCompra));

        carrito.eliminarProducto(cantidad, valor);
        return mapToCarritoResponse(carritoRepository.save(carrito));
    }

    @Override
    public void vaciarCarrito(int idCompra) {
        CarritoModel carrito = carritoRepository.findById(idCompra)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado con ID: " + idCompra));

        carrito.vaciarCarrito();
        carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(int idCompra) {
        if (!carritoRepository.existsById(idCompra)) {
            throw new CarritoNotFoundException("Carrito no encontrado con ID: " + idCompra);
        }
        carritoRepository.deleteById(idCompra);
    }

    @Override
    public List<CarritoResponse> obtenerTodosLosCarritos() {
        return carritoRepository.findAll()
                .stream()
                .map(this::mapToCarritoResponse)
                .collect(Collectors.toList());
    }

    private CarritoResponse mapToCarritoResponse(CarritoModel carrito) {
        return CarritoResponse.builder()
                .idCompra(carrito.getIdCompra())
                .cantidadProductos(carrito.getCantidadProductos())
                .valorProducto(carrito.getValorProducto())
                .valorTotal(carrito.getValorTotal())
                .build();
    }
}


