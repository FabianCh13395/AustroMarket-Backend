package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Admin;
import com.proyecto.AustroMarket.Model.Bill;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Repository.AdminRepository;
import com.proyecto.AustroMarket.Repository.BillRepository;
import com.proyecto.AustroMarket.Repository.DetailRepository;
import com.proyecto.AustroMarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/austrom/bill")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private DetailRepository detailRepository;


    @PostMapping
    public ResponseEntity<?> create (@RequestBody Bill bill){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

        billRepository.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    @GetMapping
    public List<Bill> getBill(){
        return billRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Bill> getProductById(@PathVariable(value="id")Long billId) {
        return billRepository.findById(billId);
    }
}
