package com.proyecto.AustroMarket.Controller;

import com.proyecto.AustroMarket.Model.Asset;
import com.proyecto.AustroMarket.Model.Product;
import com.proyecto.AustroMarket.Repository.ProductRepository;
import com.proyecto.AustroMarket.Services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private S3Service s3Service;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/upload/{id}")
    Map<String,String> upload(@PathVariable(value="id")Long productId,@RequestParam MultipartFile file){
        String key = s3Service.putObject(file);

        Map<String,String> result= new HashMap<>();
        result.put("key", key);
        result.put("url", s3Service.getObjectUrl(key));
        Optional<Product> p;
        p=productRepository.findById(productId);
        p.get().setImageUrl(s3Service.getObjectUrl(key));
        productRepository.save(p.get());
        return result;
    }

    @GetMapping(value = "/get-object",params ="key")
    ResponseEntity<ByteArrayResource> getObject(@RequestParam String key) {
        Asset asset = s3Service.getObject(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());

        return ResponseEntity
                .ok()
                .header("Content-Type",asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);

    }

    @DeleteMapping(value = "/delete-object",params = "key")
    void deleteObject(@RequestParam String key){
        s3Service.deleteObject(key);
    }
}
