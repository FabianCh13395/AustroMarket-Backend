package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Client;
import com.proyecto.AustroMarket.Model.Detail;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Model.User;
import com.proyecto.AustroMarket.Repository.ProductRepository;
import com.proyecto.AustroMarket.Repository.SallerRepository;
import com.proyecto.AustroMarket.Services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/austrom/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private SallerRepository sallerRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        //ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable(value="id")Long productId) {
        return productRepository.findById(productId);
    }

    @GetMapping("/find/{name}")
    public List<Product> getProductByName(@PathVariable(value="name")String value) {
        List<Product> p=new ArrayList<>();
        productRepository.findByName(value).stream().forEach(a->p.add(a));

        try {
            productRepository.findByPrice(Double.valueOf(value)).stream().forEach(a->p.add(a));
        }catch (Exception e){

        }
        return p;

    }
    @GetMapping("/findBySaller/{id}")
    public List<Product> getProductBySaller(@PathVariable(value="id")Long id) {
      return productRepository.findBySaller(sallerRepository.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product productDetail, @PathVariable(value = "id") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        double a = 0;

        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            if (!productDetail.getName().isBlank()) {
                product.get().setName(productDetail.getName());
            }
        } catch (Exception e) {

        }
        try {
            if (productDetail.getPrice() > a) {
                product.get().setPrice(productDetail.getPrice());
            }
        } catch (Exception e) {

        }
        try {
            if (!productDetail.getImageUrl().isBlank()) {
                product.get().setImageUrl(productDetail.getImageUrl());
            }
        } catch (Exception e) {

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product.get()));
    }

    @PutMapping("/avalibility/{id}")
    public ResponseEntity<?> updateAvalability(@RequestBody Product productDetail, @PathVariable(value = "id") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setAvailability(productDetail.isAvailability());

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product.get()));
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<?> updateState(@RequestBody Product productDetail, @PathVariable(value = "id") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setState(productDetail.isState());

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product.get()));
    }
}
