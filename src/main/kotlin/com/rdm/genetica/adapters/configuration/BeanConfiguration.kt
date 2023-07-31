package com.rdm.genetica.adapters.configuration

import com.rdm.genetica.GeneticAApplication
import com.rdm.genetica.application.ports.ActivityRepositoryPort
import com.rdm.genetica.application.service.ActivityService
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@ComponentScan(basePackageClasses = arrayOf(GeneticAApplication::class))
class BeanConfiguration {

    @Bean
    fun activityService(port: ActivityRepositoryPort): ActivityService {
        return ActivityService(port)
    }

    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }
}