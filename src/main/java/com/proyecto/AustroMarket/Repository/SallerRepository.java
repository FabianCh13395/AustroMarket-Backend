package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Saller;
import com.proyecto.AustroMarket.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SallerRepository extends JpaRepository<Saller, Long> {
    public List<Saller> findByBusinessName(String name);
    public List<Saller> findByUser(User user);
}
