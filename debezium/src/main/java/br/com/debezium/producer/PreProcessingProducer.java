/*
* @(#)PreProcessingProducer.java
*
* Copyright (c) J-Tech Solucoes em Informatica.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of J-Tech.
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with J-Tech.
*/
package br.com.debezium.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Class PreProcessingProducer
 *
 * @author rodrigo.cunha
 */
@Service
public class PreProcessingProducer {
    
    @Autowired
    private RoutingKafkaTemplate kafkaTemplate;  
    
    
    public void producer() {
        kafkaTemplate.send("topic-1", "Teste do topic-1");
    }
}
