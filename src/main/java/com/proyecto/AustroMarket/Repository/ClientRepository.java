package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public List<Client> findByUser(User user);
}
