package com.deiby.market.persistence;

import com.deiby.market.domain.Product;
import com.deiby.market.domain.repository.ProductRepository;
import com.deiby.market.persistence.crud.ProductoCrudRepository;
import com.deiby.market.persistence.entity.Producto;
import com.deiby.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    public Optional<List<Product>> getByStock (int quantity){
        Optional<List<Producto>> productos =  productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getPoduct(int productId) {
        return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public Producto getByEstadoTrue(Boolean estado){
        return productoCrudRepository.findByEstadoEquals(true);
    }

    public Optional<List<Producto>> getByMenorPrecioAndEstado(int precioVenta, Boolean estado){
        return productoCrudRepository.findByPrecioVentaLessThanAndEstado(precioVenta, estado);
    }

    @Override
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
