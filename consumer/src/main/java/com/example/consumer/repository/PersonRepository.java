/*
* @(#)PersonRepository.java
*
* Copyright (c) J-Tech Solucoes em Informatica.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of J-Tech.
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with J-Tech.
*/
package com.example.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.consumer.model.Person;


/**
 * Class PersonRepository
 *
 * @author rodrigo.cunha
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, Long> {

}
