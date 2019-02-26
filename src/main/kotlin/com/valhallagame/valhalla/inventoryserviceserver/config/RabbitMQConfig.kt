package com.valhallagame.valhalla.inventoryserviceserver.config

import com.valhallagame.common.rabbitmq.RabbitMQRouting
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun characterExchange() = DirectExchange(RabbitMQRouting.Exchange.CHARACTER.name)

    @Bean
    fun inventoryCharacterDeleteQueue() = Queue("inventoryCharacterDeleteQueue")

    @Bean
    fun bindingCharacterDelete(characterExchange: DirectExchange, inventoryCharacterDeleteQueue: Queue): Binding
            = BindingBuilder.bind(inventoryCharacterDeleteQueue).to(characterExchange).with(RabbitMQRouting.Character.DELETE)

    @Bean
    fun jacksonConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun containerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setMessageConverter(jacksonConverter())
        return factory
    }
}