package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Admin;
import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Model.User;
import com.proyecto.AustroMarket.Repository.AdminRepository;
import com.proyecto.AustroMarket.Repository.ClientRepository;
import com.proyecto.AustroMarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/austrom/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Admin admin){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }

    @GetMapping
    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getProductById(@PathVariable(value="id")Long adminId) {
        return adminRepository.findById(adminId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody User adminDetail, @PathVariable(value = "id") Long adminId){
        Optional<Admin> admin =adminRepository.findById(adminId);
        Optional<User> user=userRepository.findById(admin.get().getUser().getUserId());
        if(!admin.isPresent()){
            return ResponseEntity.notFound().build();
        }
        try {
            if(!adminDetail.getName().isBlank()){
                user.get().setName(adminDetail.getName());
            }
        } catch (Exception e){

        }
        try {
            if(!adminDetail.getLastName().isBlank()){
                user.get().setLastName(adminDetail.getLastName());
            }
        }catch (Exception e){

        }
        try {
            if(!adminDetail.getMail().isBlank()){
                user.get().setMail(adminDetail.getMail());
            }
        }catch (Exception e){

        }
        try {
            if(!adminDetail.getPhone().isBlank()){
                user.get().setPhone(adminDetail.getPhone());
            }
        }catch (Exception e){

        }
        /*user.get().setLastName(clientDetail.getLastName());
        user.get().setMail(clientDetail.getMail());
        user.get().setPhone(clientDetail.getPhone());*/
        try {
            System.out.println("Nuevo"+adminDetail.isState());
            System.out.println("Antiguo"+user.get().isState());
            if(adminDetail.isState() && user.get().isState()){
                System.out.println("entra if");
            }else{
                user.get().setState(adminDetail.isState());
            }
        } catch (Exception e){

        }

        userRepository.save(user.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(adminRepository.findById(admin.get().getAdminId()));
    }
}
