package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.User;
import com.proyecto.AustroMarket.Repository.ClientRepository;
import com.proyecto.AustroMarket.Repository.SallerRepository;
import com.proyecto.AustroMarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/austrom/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SallerRepository sallerRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create (@RequestBody User user){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public List<User> getUser(){
        return userRepository.findAll();
    }

    @GetMapping("/login/{mail}/{password}")
    public User Login(@PathVariable(value="mail")String mail,@PathVariable(value="password")String password){
        System.out.println("entra al metodo");
        User user1=userRepository.findByMail(mail);
        if(password.equals(user1.getPassword())){
            return user1;
        }
     return null;

    }
}
