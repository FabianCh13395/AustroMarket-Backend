package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Qualification;
import com.proyecto.AustroMarket.Model.Saller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<Qualification,Long> {
    public List<Qualification> findBySaller(Optional<Saller> saller);
    public List<Qualification> findByClient(Optional<Client> client);
}
