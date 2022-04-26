package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Model.User;
import com.proyecto.AustroMarket.Repository.ClientRepository;
import com.proyecto.AustroMarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/austrom/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Client client){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping
    public List<Client> getClient(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable(value="id")Long clientId) {
        return clientRepository.findById(clientId);
    }

    @GetMapping("/findby/{value}")
    public List<Client> getClientBy(@PathVariable(value="value")String value) {
        List<Client> c=new ArrayList<>();

        userRepository.findByName(value).forEach(a->
                clientRepository.findByUser(a).forEach(client -> c.add(client)));
        userRepository.findByLastName(value).forEach(a->
                clientRepository.findByUser(a).forEach(client -> c.add(client)));
        return c;
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody User clientDetail, @PathVariable(value = "id") Long clientId){
        Optional<Client> client =clientRepository.findById(clientId);
        Optional<User> user=userRepository.findById(client.get().getUser().getUserId());
        if(!client.isPresent()){
            return ResponseEntity.notFound().build();
        }
        try {
            if(!clientDetail.getName().isBlank()){
                user.get().setName(clientDetail.getName());
            }
        } catch (Exception e){

        }
        try {
            if(!clientDetail.getLastName().isBlank()){
                user.get().setLastName(clientDetail.getLastName());
            }
        }catch (Exception e){

        }
        try {
            if(!clientDetail.getMail().isBlank()){
                user.get().setMail(clientDetail.getMail());
            }
        }catch (Exception e){

        }
        try {
            if(!clientDetail.getPhone().isBlank()){
                user.get().setPhone(clientDetail.getPhone());
            }
        }catch (Exception e){

        }

        user.get().setState(clientDetail.isState());

        userRepository.save(user.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.findById(client.get().getClientId()));
    }
}
