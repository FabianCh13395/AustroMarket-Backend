package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Bill;
import com.proyecto.AustroMarket.Model.Detail;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Repository.BillRepository;
import com.proyecto.AustroMarket.Repository.DetailRepository;
import com.proyecto.AustroMarket.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/austrom/detail")
public class DetailController {

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Detail detail){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        detail.setSubtotal(detail.getAmount()*productRepository
                .findById(detail.getProduct().getProductId())
                .get().getPrice());
        Bill bill=billRepository.getById(detail.getBill().getBillId());
        bill.setTotal(bill.getTotal()+detail.getSubtotal());
        detailRepository.save(detail);
        return ResponseEntity.status(HttpStatus.CREATED).body(detail);
    }

    @GetMapping
    public List<Detail> getDetail(){
        return detailRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Detail> getDetailById(@PathVariable(value="id")Long detailId) {
        return detailRepository.findById(detailId);
    }
}
