package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping({"", "/"})
    public Mono<ResponseEntity<Product>> create(@RequestBody Product product) {
        return productRepository.save(product)
                .map(savedProduct -> ResponseEntity.ok(savedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getStudentById(@PathVariable String id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> updateStudent(@PathVariable String id, @RequestBody Product product) {
        return productRepository.findById(id)
                .flatMap(selectedProductFromDB -> {
                    selectedProductFromDB.setName(product.getName());
                    selectedProductFromDB.setCount(product.getCount());
                    selectedProductFromDB.setPrice(product.getPrice());

                    return productRepository.save(selectedProductFromDB);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return productRepository.findById(id)
                .flatMap(selectedStudentFromDB ->
                        productRepository.delete(selectedStudentFromDB)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
