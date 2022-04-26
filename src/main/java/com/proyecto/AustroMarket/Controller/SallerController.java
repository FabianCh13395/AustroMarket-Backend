package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Saller;
import com.proyecto.AustroMarket.Model.User;
import com.proyecto.AustroMarket.Repository.ClientRepository;
import com.proyecto.AustroMarket.Repository.SallerRepository;
import com.proyecto.AustroMarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/austrom/saller")
public class SallerController {

    @Autowired
    private SallerRepository sallerRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create (@RequestBody Saller saller){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

        sallerRepository.save(saller);
        return ResponseEntity.status(HttpStatus.CREATED).body(saller);
    }

    @GetMapping
    public List<Saller> getSaller(){
        return sallerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Saller> getClientById(@PathVariable(value="id")Long sallerId) {
        return sallerRepository.findById(sallerId);
    }

    @GetMapping("/findby/{value}")
    public List<Saller> getClientBy(@PathVariable(value="value")String value) {
        List<Saller> c=new ArrayList<>();

        userRepository.findByName(value).forEach(a->
                sallerRepository.findByUser(a).forEach(saller -> c.add(saller)));
        userRepository.findByLastName(value).forEach(a->
                sallerRepository.findByUser(a).forEach(saller -> c.add(saller)));

        sallerRepository.findByBusinessName(value).stream().forEach(saller -> c.add(saller));
        return c;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Saller sallerDetail, @PathVariable(value = "id") Long sallerId){
        Optional<Saller> saller =sallerRepository.findById(sallerId);
        //Optional<User> user=userRepository.findById(client.get().getUser().getUserId());
        if(!saller.isPresent()){
            return ResponseEntity.notFound().build();
        }
        try {
            if(!sallerDetail.getUser().getName().isBlank()){
                saller.get().getUser().setName(sallerDetail.getUser().getName());
            }
        } catch (Exception e){

        }
        try {
            if(!sallerDetail.getUser().getLastName().isBlank()){
                saller.get().getUser().setLastName(sallerDetail.getUser().getLastName());
            }
        }catch (Exception e){

        }
        try {
            if(!sallerDetail.getUser().getMail().isBlank()){
                saller.get().getUser().setMail(sallerDetail.getUser().getMail());
            }
        }catch (Exception e){

        }
        try {
            if(!sallerDetail.getUser().getPhone().isBlank()){
                saller.get().getUser().setPhone(sallerDetail.getUser().getPhone());
            }
        }catch (Exception e){

        }

        try {
            if(!sallerDetail.getUser().getPhone().isBlank()){
                saller.get().getUser().setPhone(sallerDetail.getUser().getPhone());
            }
        }catch (Exception e){

        }

        try {
            if(!sallerDetail.getAddress().isBlank()){
                saller.get().setAddress(sallerDetail.getAddress());
            }
        }catch (Exception e){

        }

        try {
            if(!sallerDetail.getBusinessName().isBlank()){
                saller.get().setBusinessName(sallerDetail.getBusinessName());
            }
        }catch (Exception e){

        }

        try {
            if(sallerDetail.getTransport()>0){
                saller.get().setTransport(sallerDetail.getTransport());
            }
        }catch (Exception e){

        }

        saller.get().getUser().setState(sallerDetail.getUser().isState());

        return ResponseEntity.status(HttpStatus.CREATED).body(sallerRepository.save(saller.get()));
    }
}
