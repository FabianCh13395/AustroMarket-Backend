package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Detail;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Model.Qualification;
import com.proyecto.AustroMarket.Repository.ProductRepository;
import com.proyecto.AustroMarket.Repository.QualificationRepository;
import com.proyecto.AustroMarket.Repository.SallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/austrom/qualification")
public class QualificationController {

    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private SallerRepository sallerRepository;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Qualification qualification){
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

        qualificationRepository.findAll().forEach(s->{
            if(s.getClient().getClientId()==qualification.getClient().getClientId()&
            s.getSaller().getSallerId()==qualification.getSaller().getSallerId()){
                return;
        }
        });

        qualificationRepository.save(qualification);
        return ResponseEntity.status(HttpStatus.CREATED).body(qualification);
    }

    @GetMapping
    public List<Qualification> getQualification(){
        return qualificationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Qualification> getDetailById(@PathVariable(value="id")Long qualificationId) {
        return qualificationRepository.findById(qualificationId);
    }

    @GetMapping("/findbySaller/{value}")
    public List<Qualification> getClientBy(@PathVariable(value="value")long value) {
        List<Qualification> c=new ArrayList<>();
        c=qualificationRepository.findBySaller(sallerRepository.findById(value));

        return c;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Qualification qualificationDetail, @PathVariable(value = "id") Long qualificationId) {
        Optional<Qualification> qualification = qualificationRepository.findById(qualificationId);
        double a = 0;

        if (!qualification.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            if (qualificationDetail.getQualification()>0) {
                qualification.get().setQualification(qualificationDetail.getQualification());
            }
        } catch (Exception e) {

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(qualificationRepository.save(qualification.get()));
    }
}
