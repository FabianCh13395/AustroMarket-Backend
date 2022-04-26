package com.proyecto.AustroMarket.Repository;

import com.proyecto.AustroMarket.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByName(String name);
    public List<User> findByLastName(String lastName);
    public User findByMail(String mail);
}
