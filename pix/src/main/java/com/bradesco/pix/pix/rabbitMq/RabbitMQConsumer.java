package com.bradesco.pix.pix.rabbitMq;

import com.bradesco.pix.pix.domain.RequisicaoPagamento;
import com.bradesco.pix.pix.service.RequisicaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    private RequisicaoService service;

    @RabbitListener(queues = {"${queue.order.name}"})
    public void receber(@Payload String resultado) {
        ObjectMapper mapper = new ObjectMapper();
        RequisicaoPagamento requisicaoPagamento;
        try {
            requisicaoPagamento = mapper.readValue(resultado, RequisicaoPagamento.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        service.update(requisicaoPagamento);
    }
}