package com.deiby.market.persistence;

import com.deiby.market.domain.Purchase;
import com.deiby.market.domain.repository.PurchaseRepository;
import com.deiby.market.persistence.crud.CompraCrudRepository;
import com.deiby.market.persistence.entity.Compra;
import com.deiby.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;
    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
/*        Compra compra = compraCrudRepository.save(mapper.toCompra(purchase));
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compra);*/
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return  mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
