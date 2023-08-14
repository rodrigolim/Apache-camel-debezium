/*
* @(#)EventProducer.java
*
* Copyright (c) J-Tech Solucoes em Informatica.
* All Rights Reserved.
*
* This software is the confidential and proprietary information of J-Tech.
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with J-Tech.
*/
package br.com.debezium.agregate;

/**
 * Class EventProducer
 *
 * @author rodrigo.cunha
 */
public interface EventProducer {
    /**
     * Publish event on broker messages, like kafka or RabbitMQ.
     *
     * @param topic Topic to publish the message.
     * @param event Event to publish extended {@link BaseEvent}.
     */
    void producer(String topic, String message);
}
