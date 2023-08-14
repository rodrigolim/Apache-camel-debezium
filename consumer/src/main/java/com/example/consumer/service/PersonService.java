/*
* @(#)PersonService.java
*
* Copyright (c) J-Tech Solucoes em Informatica.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of J-Tech.
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with J-Tech.
*/
package com.example.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.consumer.model.Person;
import com.example.consumer.repository.PersonRepository;

/**
 * Class PersonService
 *
 * @author rodrigo.cunha
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonRepository repository;
    
    public void save(Person person) {
        this.repository.save(person);
    }

}
