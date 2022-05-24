package de.hfu.cnc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("cnc.direct");
    }

    @Bean
    public Queue queue(@Value("${service.routingKey}") String routingKey) {
        Queue queue = new Queue("cnc." + routingKey);
        queue.addArgument("x-queue-type", "quorum");
        return queue;
    }

    @Bean
    public Binding binding1(DirectExchange direct, Queue queue,
                            @Value("${service.routingKey}") String routingKey) {
        return BindingBuilder.bind(queue)
                .to(direct)
                .with(routingKey);
    }

}