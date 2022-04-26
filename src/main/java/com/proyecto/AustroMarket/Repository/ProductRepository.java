package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Model.Saller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findByName(String name);
    public List<Product> findByPrice(double price);
    public List<Product> findBySaller(Optional<Saller> saller);
}
