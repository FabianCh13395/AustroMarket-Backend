package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
