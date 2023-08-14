///*
//* @(#)PreProcessingDataProducer.java
//*
//* Copyright (c) J-Tech Solucoes em Informatica.
//* All Rights Reserved.
//*
//* This software is the confidential and proprietary information of J-Tech.
//* ("Confidential Information"). You shall not disclose such Confidential
//* Information and shall use it only in accordance with the terms of the
//* license agreement you entered into with J-Tech.
//*/
//package br.com.debezium.producer;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.RoutingKafkaTemplate;
//import org.springframework.stereotype.Service;
//import br.com.debezium.agregate.EventProducer;
//
///**
// * Class PreProcessingDataProducer
// *
// * @author rodrigo.cunha
// */
//
//@Service
//@RequiredArgsConstructor
//public class PreProcessingDataProducer implements EventProducer {
//    
//    @Autowired
//    private RoutingKafkaTemplate kafkaTemplate;
//
//    @Override
//    public void producer(String topic, String message) {
//        this.kafkaTemplate.send(topic, message);
//    }
//}
