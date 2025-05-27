package com.JLC_ITsolutions.carrito_de_compras.repository;

import com.JLC_ITsolutions.carrito_de_compras.model.CarritoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface CarritoRepository extends JpaRepository<CarritoModel, Integer> {

}
