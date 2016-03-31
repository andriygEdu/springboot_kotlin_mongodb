package eu.andriydc.edu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.spi.DocumentationType
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.service.ApiInfo
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@SpringBootApplication
@EnableSwagger2
@ComponentScan()
open class Application {
    @Bean
    open fun newsApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/.*"))
                .build();
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .version("0.1.0")
                .build();
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            SpringApplication.run(Application::class.java, *args)
        }
    }
}