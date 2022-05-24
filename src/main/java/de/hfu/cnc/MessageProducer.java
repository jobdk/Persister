package de.hfu.cnc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class MessageProducer {
    private int counter = 0;
    private final RabbitTemplate template;
    private final DirectExchange direct;
    private final String routingKey;

    @Autowired
    public MessageProducer(RabbitTemplate template,
                           DirectExchange direct,
                           @Value("${service.routingKey}") String routingKey) {
        this.template = template;
        this.direct = direct;
        this.routingKey = routingKey;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 5000)
    public void send() {
        String message = routingKey + " Message " + counter++;
        template.convertAndSend(direct.getName(), routingKey, message);
    }
}
