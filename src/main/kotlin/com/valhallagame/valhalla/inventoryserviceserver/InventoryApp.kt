package com.valhallagame.valhalla.inventoryserviceserver

import com.valhallagame.common.DefaultServicePortMappings
import com.valhallagame.common.filter.ServiceRequestFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import java.io.FileInputStream
import java.util.*

@SpringBootApplication
class InventoryApp {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(InventoryApp::class.java)
    }

    @Bean
    fun customizer() = WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
        it.setPort(DefaultServicePortMappings.INVENTORY_SERVICE_PORT)
    }

    @Bean
    fun requestFilterRegistration(): FilterRegistrationBean<ServiceRequestFilter> {
        return FilterRegistrationBean<ServiceRequestFilter>().apply {
            filter = getServiceRequestFilter()
            addUrlPatterns(
                    "/*",
                    "/**"
            )
            setName("serviceRequestFilter")
            order = 1
        }
    }

    @Bean(name = ["serviceRequestFilter"])
    fun getServiceRequestFilter(): ServiceRequestFilter {
        return ServiceRequestFilter()
    }
}

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        if (InventoryApp.logger.isInfoEnabled) {
            InventoryApp.logger.info("Args passed in: {}", Arrays.asList(args))
        }

        args.forEach {
            val split = it.split("=")

            if (split.size == 2) {
                System.getProperties().setProperty(split[0], split[1])
            } else {
                FileInputStream(args[0]).use {fis ->
                    System.getProperties().load(fis)
                }
            }
        }
    } else {
        InventoryApp.logger.info("No args passed to main")
    }

    runApplication<InventoryApp>(*args)
}
