package com.deiby.market.persistence;

import com.deiby.market.persistence.crud.ProductoCrudRepository;
import com.deiby.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria (Integer idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getByStock (int cantidadStock){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
    }

    public Producto getByEstadoTrue(Boolean estado){
        return productoCrudRepository.findByEstadoEquals(true);
    }

    public Optional<List<Producto>> getByMenorPrecioAndEstado(int precioVenta, Boolean estado){
        return productoCrudRepository.findByPrecioVentaLessThanAndEstado(precioVenta, estado);
    }

    public Optional<Producto> getProducto (int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
