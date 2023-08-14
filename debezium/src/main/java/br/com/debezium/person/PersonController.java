/*
* @(#)PersonController.java
*
* Copyright (c) J-Tech Solucoes em Informatica.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of J-Tech.
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with J-Tech.
*/
package br.com.debezium.person;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * Class PersonController
 *
 * @author rodrigo.cunha
 */
@RestController
@RequestMapping("person")
public class PersonController {
    
    @Autowired
    private PersonRepository repository;
    
    @Autowired
    private RoutingKafkaTemplate kafkaTemplate;   
  

    
    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Person person){
        try {
            person = repository.save(person);
         //  kafkaTemplate.send("person-topic", person); 
           
         //  kafkaTemplate.send("topic-1", "Teste do topic-1");
           
            return ResponseEntity.ok(person); 
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Long id,
                                 @RequestBody Person person){
        return repository.findById(id).map(p -> {
            p.setName(person.getName());
            p.setAge(person.getAge());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());  
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    
}
